package com.scsxyz.java.generator.config;

import java.io.Serializable;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public class Module implements Serializable {

    private String table;
    private String entity;
    private String packageName;
    private boolean frontend;
    private FrontendConfig frontendConfig;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public boolean isFrontend() {
        return frontend;
    }

    public void setFrontend(boolean frontend) {
        this.frontend = frontend;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public FrontendConfig getFrontendConfig() {
        return frontendConfig;
    }

    public void setFrontendConfig(FrontendConfig frontendConfig) {
        this.frontendConfig = frontendConfig;
    }

    public String getClassName() {
        return packageName + ".entity." + entity;
    }
}
