/*
 * Copyright (c) 2013. Bond(China)
 */
package ${root.basePackage}.common.json.annotation;

import java.lang.annotation.*;

/**
 * Use to how to fast access object's attribute
 *
 * @author Bond(China)
 * @version 1.0.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JsonPathValue {

    enum DataType {
        /**
         * 一般值类型
         */
        VALUE,
        /**
         * 对象类型
         */
        OBJECT,
        /**
         * 数组类型
         */
        LIST,
        /**
         * 日期类型
         */
        DATE
    }

    /**
     * The path of a JSONObject
     *
     * @return
     */
    String path() default "";

    /**
     * Default Type
     *
     * @return
     */
    DataType dataType() default DataType.VALUE;

    /**
     * Date format, define for date type.
     *
     * @return
     */
    String pattern() default "yyyy-MM-dd HH:mm:ss";

}