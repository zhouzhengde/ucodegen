/*
 * Copyright (c) 2013. Bond(China)
 */
package ${root.basePackage}.common.json;

import com.jayway.jsonpath.JsonPath;
import ${root.basePackage}.common.json.annotation.JsonPathValue;
import ${root.basePackage}.common.util.Helper;
import ${root.basePackage}.common.util.DateUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * JSON操作工具
 *
 * @author Bond(China)
 * @version 1.0.0
 */
public final class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private JsonUtils() {
    }

    /**
     * 将JSON对象转换成JavaBean
     *
     * @param jsonObject
     * @param clz
     * @param <T>
     * @return
     */
    public static <T extends Serializable> T toBean(JSONObject jsonObject, Class<T> clz) {

        if (jsonObject == null) {
            return null;
        }

        T instance = null;
        try {
            instance = clz.newInstance();

            Field[] fields = clz.getDeclaredFields();

            if (fields == null || fields.length == 0) {
                return instance;
            }

            for (Field field : fields) {

                injectJsonValue(jsonObject, instance, field);
            }
        } catch (Exception e) {
            LOGGER.warn("[JsonUtils toBean]", e.getMessage());
        }
        return instance;
    }

    /**
     * 为JSON对象设置值
     *
     * @param json
     * @param key   比如：{"user":{"name": "xx"}},可以使用：user.name,
     * @param value
     */
    public static void setValue(JSONObject json, String key, Object value) {
        try {
            String[] split = key.split("\\.");
            if (Helper.isEqual(split.length, 1)) {
                json.put(key, value);
                return;
            }
            for (int i = 0; i < split.length; i++) {
                String k = split[i];
                if (k.contains("[") && k.contains("]")) {
                    k = k.substring(0, k.indexOf("["));
                }
                Object o = json.get(k);
                if (Helper.isNull(o)) {
                    break;
                }
                if (o instanceof JSONArray) {
                    JSONArray array = (JSONArray) o;
                    int index = Integer.valueOf(key.substring(key.indexOf("[") + 1, key.indexOf("]")));
                    o = array.get(index);
                }
                String subKey = key.substring(key.indexOf(".") + 1, key.length());
                setValue((JSONObject) o, subKey, value);
            }
        } catch (Exception e) {
            LOGGER.warn("JSON set value", e);
        }
    }

    /**
     * Inject value to attribute of instance
     *
     * @param jsonObject
     * @param instance
     * @param field
     * @param <T>
     */
    private static <T extends Serializable> void injectJsonValue(JSONObject jsonObject, T instance, Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();

        if (annotations == null || annotations.length == 0) {
            return;
        }

        for (Annotation annotation : annotations) {

            if (annotation.annotationType().equals(JsonPathValue.class)) {

                JsonPathValue value = (JsonPathValue) annotation;
                injectJsonValue(instance, jsonObject, field, value);
            }
        }
    }

    private static void injectJsonValue(Object instance, JSONObject jsonObject, Field field,
                                        JsonPathValue jsonPathValue) {

        switch (jsonPathValue.dataType()) {
            case VALUE:
                injectToValue(instance, jsonObject, field, jsonPathValue);
                break;
            case OBJECT:
                injectToObject(instance, jsonObject, field, jsonPathValue);
                break;
            case LIST:
                injectToList(instance, jsonObject, field, jsonPathValue);
                break;
            case DATE:
                injectToDate(instance, jsonObject, field, jsonPathValue);
                break;
            default:
                LOGGER.warn("[JsonPathValue], dataType is undefined");
                break;
        }
    }

    /**
     * Inject To date
     *
     * @param instance
     * @param jsonObject
     * @param field
     * @param jsonPathValue
     */
    private static void injectToDate(Object instance, JSONObject jsonObject, Field field, JsonPathValue jsonPathValue) {
        try {
            Object value = getValue(jsonObject, getPath(jsonPathValue));
            String pattern = jsonPathValue.pattern();
            if (field.getType().equals(Date.class)) {
                if (value instanceof String) {
                    value = DateUtils.parse((String) value, pattern);
                } else if (value instanceof Integer) {
                    value = new Date((Integer) value);
                } else if (value instanceof Long) {
                    value = new Date((Long) value);
                }
            }
            injectValue(instance, field, value);
        } catch (Exception e) {
            LOGGER.warn("[JsonPathValue], injectToDate", e.getMessage());
        }
    }

    /**
     * Inject to List
     *
     * @param instance
     * @param jsonObject
     * @param field
     * @param jsonPathValue
     */
    @SuppressWarnings("unchecked")
    private static void injectToList(Object instance, JSONObject jsonObject, Field field, JsonPathValue jsonPathValue) {

        try {
            if (!field.getType().isAssignableFrom(List.class)) {
                LOGGER.warn("Not match data type");
                return;
            }

            Type genType = field.getGenericType();

            if (genType == null) {
                LOGGER.warn("The List attribute not genericType");
                return;
            }

            Class<Serializable> genClz = null;
            if (genType instanceof ParameterizedType) {
                genClz = (Class<Serializable>) ((ParameterizedType) genType).getActualTypeArguments()[0];
            }

            if (genClz != null) {
                injectToList(instance, jsonObject, field, jsonPathValue, genClz);
            }
        } catch (Exception e) {
            LOGGER.warn("[JsonPathValue], injectToList", e.getMessage());
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void injectToList(Object instance, JSONObject jsonObject, Field field, JsonPathValue jsonPathValue,
                                     Class<Serializable> genClz) {
        Object arrayObject = getValue(jsonObject, getPath(jsonPathValue));
        if (arrayObject == null) {
            return;
        }
        List rsList = new ArrayList();
        JSONArray jsonArray = null;
        if (arrayObject instanceof JSONArray) {

            jsonArray = (JSONArray) getValue(jsonObject, getPath(jsonPathValue));
            rsList = injectToListByClass(jsonArray, genClz);
            injectValue(instance, field, rsList);
        } else if (arrayObject instanceof JSONObject) {

            JSONObject jsonObject2 = (JSONObject) arrayObject;
            Set<Object> keys = jsonObject2.keySet();
            if (keys.size() == 1) {
                arrayObject = getOneInArray(jsonObject2);
            }
            Object childObject = toBean((JSONObject) arrayObject, genClz);
            rsList.add(childObject);
            injectValue(instance, field, rsList);
        } else {
            rsList.add(arrayObject);
            injectValue(instance, field, rsList);
        }
    }

    /**
     * Get on object in array
     *
     * @param jsonObject2
     * @return
     */
    @SuppressWarnings("unchecked")
    private static Object getOneInArray(JSONObject jsonObject2) {

        Iterator<Object> iterator = jsonObject2.keys();
        Object rs = null;
        while (iterator.hasNext()) {
            Object key = iterator.next();
            rs = jsonObject2.get(key);
        }
        return rs;
    }

    /**
     * Inject to List By genClz
     *
     * @param jsonArray
     * @param genClz
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static List injectToListByClass(JSONArray jsonArray, Class<Serializable> genClz) {

        if (jsonArray == null || jsonArray.isEmpty()) {
            return Collections.emptyList();
        }

        List rsList = new ArrayList();

        for (Object object : jsonArray) {
            if (genClz.isPrimitive() || genClz.equals(String.class)) {
                rsList.add(object);
                continue;
            }
            Object childObject = toBean((JSONObject) object, genClz);
            rsList.add(childObject);
        }
        return rsList;
    }

    /**
     * Inject to an Object
     *
     * @param instance
     * @param jsonObject
     * @param field
     * @param jsonPathValue
     */
    @SuppressWarnings("unchecked")
    private static void injectToObject(Object instance, JSONObject jsonObject, Field field,
                                       JsonPathValue jsonPathValue) {
        try {

            JSONObject subJsonObject = JsonPath.read(jsonObject, getPath(jsonPathValue));

            Object value = toBean(subJsonObject, (Class<Serializable>) field.getType());

            injectValue(instance, field, value);

        } catch (Exception e) {
            LOGGER.warn("[JsonPathValue injectToValue]", e.getMessage());
        }
    }

    /**
     * Inject basic type value
     *
     * @param instance
     * @param jsonObject
     * @param field
     * @param jsonPathValue
     */
    private static void injectToValue(Object instance, JSONObject jsonObject, Field field,
                                      JsonPathValue jsonPathValue) {
        try {
            Object value = getValue(jsonObject, getPath(jsonPathValue));
            injectValue(instance, field, value);
        } catch (Exception e) {
            LOGGER.warn("[JsonPathValue injectToValue]", e.getMessage());
        }
    }

    /**
     * Inject Value
     *
     * @param instance
     * @param field
     * @param value
     */
    private static void injectValue(Object instance, Field field, Object value) {

        try {
            Class<?> type = field.getType();
            field.setAccessible(true);
            if (value != null && !Helper.isEqual("null", value.toString()) && !Helper.isEqual("[]", value.toString())) {
                field.set(instance, covert(type, value));
            }
        } catch (Exception e) {
            LOGGER.warn("[JsonUtils Inject Value] [Name]:" + field.getName() + "; [Value]:" + value.toString(), e);
        } finally {
            field.setAccessible(false);
        }
    }

    /**
     * Convert type & value
     *
     * @param type
     * @param value
     * @return
     */
    private static Object covert(Class<?> type, Object value) {
        if (Helper.isEqual(type.getName(), String.class.getName())) {
            return value.toString();
        }
        return value;
    }

    /**
     * Get value in jsonObject
     *
     * @param jsonObject
     * @param path
     * @return
     */
    private static Object getValue(JSONObject jsonObject, String path) {

        try {
            return JsonPath.read(jsonObject, path);
        } catch (Exception e) {
            LOGGER.warn("[JsonUtils getValue]", e.getMessage());
            return null;
        }
    }

    /**
     * Get path value
     *
     * @param jsonPathValue
     * @return
     */
    private static String getPath(JsonPathValue jsonPathValue) {
        return "Helper." + jsonPathValue.path();
    }
}