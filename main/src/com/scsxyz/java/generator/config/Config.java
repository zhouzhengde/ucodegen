package com.scsxyz.java.generator.config;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public class Config implements Serializable {

    private String workDir;

    private String basePackage;

    private String baseEntity;

    private String serviceException;

    private String exceptionCode;

    private String baseController;

    private String pager;

    private Database database;

    private String author;

    private String dollar = "$";

    private String appName = "app";

    private String applicationRunner;

    private List<Module> moduleList;

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public List<Module> getModuleList() {
        if (moduleList == null && moduleList.isEmpty()) {
            return null;
        }
        moduleList.forEach((item) -> {
            if (item.getPackageName() == null || item.getPackageName().isEmpty()) {
                item.setPackageName("integration");
            }
            if (item.getPackageName().contains(".")) {
                return;
            }
            item.setPackageName(this.getBasePackage() + "." + item.getPackageName().trim().replaceAll(" ", "").replaceAll("_", "").replaceAll("-", ""));
        });
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public String getWorkDir() {
        return workDir;
    }

    public void setWorkDir(String workDir) {
        this.workDir = workDir;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(String baseEntity) {
        this.baseEntity = baseEntity;
    }

    public String getPager() {
        return pager;
    }

    public void setPager(String pager) {
        this.pager = pager;
    }

    public String getServiceException() {
        return serviceException;
    }

    public void setServiceException(String serviceException) {
        this.serviceException = serviceException;
    }

    public String getShortServiceException() {
        return this.serviceException.substring(this.serviceException.lastIndexOf(".") + 1);
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getBaseController() {
        return baseController;
    }

    public void setBaseController(String baseController) {
        this.baseController = baseController;
    }

    public String getDollar() {
        return dollar;
    }

    public void setDollar(String dollar) {
        this.dollar = dollar;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getApplicationRunner() {
        return applicationRunner;
    }

    public void setApplicationRunner(String applicationRunner) {
        this.applicationRunner = applicationRunner;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
