package ${root.basePackage}.common.base;

import ${root.basePackage}.common.entity.Pager;
import ${root.basePackage}.common.json.JsonUtils;
import ${root.basePackage}.common.util.Constants;
import ${root.basePackage}.common.util.Helper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 扁平化接口响应
 *
 * @author zhouzhengde(CN)
 */
public class Result implements Serializable {

    private String code;

    private String msg;

    private Integer status;

    private Object result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    private Result() {
    }

    /**
     * 将Result Map中的结果映射到Result，请注意：不要往Result Map中添加一级非标结果，否则将被忽略，一级标准结果有：
     * |-result
     * |-code
     * |-status
     * |-msg
     *
     * @param src
     * @param clz
     * @param <E>
     * @return
     */
    public static <E extends Serializable> Result newInstance(Map<String, Object> src, final Class<E> clz) {

        Result rs = new Result();
        rs.setCode((String) src.get(Constants.REST_CODE));
        rs.setMsg((String) src.get(Constants.REST_MESSAGE));
        rs.setStatus((Integer) src.get(Constants.REST_STATUS));

        Object result = src.get(Constants.REST_RESULT);
        if (Helper.isNull(result) || Helper.isNull(clz)) {
            return rs;
        }
        if (result instanceof Pager) {
            rs.setResult(handlePager(clz, (Pager<?>) result));
        } else if (result instanceof List) {
            rs.setResult(handleArray(clz, result));
        } else {
            JSONObject jsonObject = JSONObject.fromObject(result);
            rs.setResult(JsonUtils.toBean(jsonObject, clz));
        }
        return rs;
    }

    /**
     * 处理数组
     *
     * @param clz
     * @param result
     * @param <E>
     * @return
     */
    private static <E extends Serializable> List<E> handleArray(Class<E> clz, Object result) {
        List<E> listResult = new ArrayList<>();
        JSONArray arr = JSONArray.fromObject(result);
        arr.forEach((item) -> {
            listResult.add(JsonUtils.toBean(JSONObject.fromObject(item), clz));
        });
        return listResult;
    }

    /**
     * 处理Pager数据
     *
     * @param clz
     * @param result
     * @param <E>
     * @return
     */
    private static <E extends Serializable> Pager<E> handlePager(Class<E> clz, Pager<?> result) {
        Pager<?> pager = result;
        Pager<E> resultPager = JsonUtils.toBean(JSONObject.fromObject(pager), Pager.class);
        if (!Helper.isNull(pager.getCondition())) {
            JSONObject jsonObject = JSONObject.fromObject(pager.getCondition());
            resultPager.setCondition(JsonUtils.toBean(jsonObject, clz));
        }
        List<?> rows = pager.getRows();
        List<E> rowsResult = new ArrayList<>();
        if (!Helper.isNull(rows)) {
            rows.forEach((item) -> {
                rowsResult.add(JsonUtils.toBean(JSONObject.fromObject(item), clz));
            });
            resultPager.setRows(rowsResult);
        }
        return resultPager;
    }
}