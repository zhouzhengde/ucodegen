package com.scsxyz.java.generator.codegen.xml;

import com.scsxyz.java.generator.config.Module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 表的定义
 * Created by Bond(China) on 2017/9/7.
 */
public class Schema implements Serializable {

    /**
     * 表名
     */
    private String schemaName;

    /**
     * Java类型
     */
    private String typeName;

    /**
     * 属性列表
     */
    private List<SchemaField> fieldList;

    /**
     *
     */
    private Module module;

    /**
     * 是否采用逻辑删除
     */
    private boolean useLogicDel = true;

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<SchemaField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<SchemaField> fieldList) {
        this.fieldList = fieldList;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<SchemaField> getPKList() {
        if (fieldList == null || fieldList.size() == 0) {
            return null;
        }
        List<SchemaField> rs = new ArrayList<>();
        for (int i = 0; i < fieldList.size(); i++) {
            if (fieldList.get(i).getPK()) {
                rs.add(fieldList.get(i));
            }
        }
        return rs;
    }

    public boolean getUseLogicDel() {
        return useLogicDel;
    }

    public void setUseLogicDel(boolean useLogicDel) {
        this.useLogicDel = useLogicDel;
    }
}
