package com.scsxyz.java.generator.codegen.xml;

import com.scsxyz.java.generator.codegen.xml.impl.MySQLGenerator;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public enum Dialect {

    MySQL("mysql", new MySQLGenerator(), "SELECT DISTINCT COLUMN_NAME AS `Field`,DATA_TYPE AS `Type`,IS_NULLABLE AS `Null`,COLUMN_KEY AS `Key`,COLUMN_COMMENT AS `Comment`,COLUMN_DEFAULT AS `Default`,EXTRA AS `Extra` FROM information_schema.COLUMNS WHERE table_name = ?");

    private String name;
    private String schemaSQL;
    private SQLGenerator sqlGenerator;

    private Dialect(String name, SQLGenerator generator, String schemaSQL) {
        this.name = name;
        this.sqlGenerator = generator;
        this.schemaSQL = schemaSQL;
    }

    public SQLGenerator getSqlGenerator() {
        return sqlGenerator;
    }

    /**
     * 通过名称获取一个Dialect
     *
     * @param name
     * @return
     */
    public static Dialect getByName(String name) {
        Dialect[] values = Dialect.values();
        for (int i = 0; i < values.length; i++) {
            if (name.equalsIgnoreCase(values[i].name)) {
                return values[i];
            }
        }
        return MySQL;
    }

    public String getSchemaSQL() {
        return schemaSQL;
    }
}
