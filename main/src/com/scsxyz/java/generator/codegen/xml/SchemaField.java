package com.scsxyz.java.generator.codegen.xml;

import com.scsxyz.java.generator.util.StringUtils;
import com.scsxyz.java.generator.codegen.java.standard.Type;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public class SchemaField {

    private String name;

    private String type;

    private String key;

    private boolean PK;

    private String comment;

    private String property;

    private Type propertyType;

    private String propertyValue;

    public String getName() {
        return name;
    }

    public String getType() {
        return this.type;
    }

    public void setName(String name) {
        if (name == null || name.trim() == "") {
            return;
        }
        this.name = name;
        this.property = StringUtils.transformCamel(name);
        this.propertyValue = "#{" + this.property + "}";
    }

    public void setType(String type) {
        if (type == null) {
            return;
        }
        type = type.toUpperCase();
        if (type.indexOf("(") > 0) {
            type = type.substring(0, type.indexOf("("));
        }
        this.type = type;
        this.propertyType = Type.getByJdbcType(this.type);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
        this.PK = "PRI".equalsIgnoreCase(this.key);
    }

    public boolean getPK() {
        return PK;
    }

    public String getColumn() {
        return name;
    }

    public String getProperty() {
        return this.property;
    }

    public String getPropertyValue() {
        return this.propertyValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Type getPropertyType() {
        return this.propertyType;
    }
}
