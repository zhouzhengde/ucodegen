package com.scsxyz.java.generator.config;

import java.io.Serializable;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public class Database implements Serializable {

    private String dialect;
    private String url;
    private String username;
    private String password;
    private String driver;

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
