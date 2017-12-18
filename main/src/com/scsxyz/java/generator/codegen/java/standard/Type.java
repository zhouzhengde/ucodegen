package com.scsxyz.java.generator.codegen.java.standard;

/**
 * Created by Bond(China) on 2017/11/4.
 */
public enum Type {

    BIT("bit", "java.lang.Boolean", "Boolean"),
    CHAR("char", "java.lang.Char", "Char"),
    BLOB("blob", "java.lang.Byte", "Byte[]"),
    DECIMAL("decimal", "java.math.BigDecimal", "BigDecimal"),

    DATETIME("datetime", "java.util.Date", "Date"),
    TIMESTAMP("timestamp", "java.util.Date", "Date"),
    TIME("timestamp", "java.util.Date", "Date"),
    DATE("date", "java.util.Date", "Date"),
    YEAR("year", "java.util.Date", "Date"),

    TEXT("text", "java.lang.String", "String"),
    VARCHAR("char", "java.lang.String", "String"),

    NUMBER("double", "java.lang.Double", "Double"),
    DOUBLE("double", "java.lang.Double", "Double"),

    FLOAT("smallint", "java.lang.Float", "Float"),
    BIGINT("bigint", "java.lang.Long", "Long"),

    MEDIUMINT("mediumint", "java.lang.Integer", "Integer"),
    TINYINT("tinyint", "java.lang.Integer", "Integer"),
    SMALLINT("smallint", "java.lang.Integer", "Integer"),
    INT("int", "java.lang.Integer", "Integer");

    private String jdbcType;
    private String fullName;
    private String name;

    private Type(String jdbcType, String fullName, String name) {
        this.jdbcType = jdbcType;
        this.fullName = fullName;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public static Type getByJdbcType(String jdbcType) {
        Type[] values = Type.values();
        for (int i = 0; i < values.length; i++) {
            Type value = values[i];
            if (value.getJdbcType().equalsIgnoreCase(jdbcType)) {
                return value;
            }
        }
        return VARCHAR;
    }
}
