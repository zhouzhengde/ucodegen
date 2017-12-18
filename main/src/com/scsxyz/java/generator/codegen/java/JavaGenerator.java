package com.scsxyz.java.generator.codegen.java;

import com.scsxyz.java.generator.codegen.AbstractGenerator;
import com.scsxyz.java.generator.codegen.java.standard.JavaFile;
import com.scsxyz.java.generator.codegen.xml.Schema;
import com.scsxyz.java.generator.codegen.xml.SchemaField;
import com.scsxyz.java.generator.config.Config;
import com.scsxyz.java.generator.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public abstract class JavaGenerator extends AbstractGenerator {


    protected JavaFile javaFile;

    public JavaFile getJavaFile() {
        if (javaFile == null) {
            JavaFile.Comment comment = new JavaFile.Comment("Copyright of Bond Zhou(CN). 版本所有.归属Bond Zhou（中国）");
            if (getConfig() != null) {
                comment.setAuthor(getConfig().getAuthor());
            }
            javaFile = new JavaFile(comment.toComment());
        }
        return javaFile;
    }

    public abstract void configure(Config config, Schema schema);


    protected String getJavaFileName() {
        JavaFile javaFile = getJavaFile();
        String fileName = javaFile.getPackageName().replace(".", "/") + "/" + javaFile.getClassName() + ".java";
        return fileName;
    }

    protected void addFileComment(JavaFile.Comment comment) {
        getJavaFile().setFileComment(comment.toComment());
    }

    protected void setMethodComment(JavaFile.Method method, JavaFile.Comment comment){
        comment.setAuthor(getConfig().getAuthor());
        comment.setParams(method.getParameterList());
        comment.setReturnType(method.getReturnType());
        method.setComment(comment.toComment());
    }

    /**
     * 添加类的注释
     *
     * @param comment
     */
    protected void addClassComment(JavaFile.Comment comment) {
        comment.setAuthor(getConfig().getAuthor());
        getJavaFile().setClassComment(comment.toComment());
    }

    /**
     * 添加继承类
     *
     * @param fullName
     */
    protected void addExtends(String fullName) {
        String name = fullName.substring(fullName.lastIndexOf(".") + 1);
        getJavaFile().setExtendsName(name);
        addImport(fullName);
    }

    /**
     * 添加导包
     *
     * @param imp
     */
    protected void addImport(String imp) {
        if (imp.contains("java.lang.")) {
            return;
        }
        if (imp.contains("[")) {
            return;
        }
        if (imp.contains("<")) {
            return;
        }
        getJavaFile().getImportList().add(imp);
    }

    /**
     * 通用添加属性
     *
     * @param property
     */
    protected void addProperty(JavaFile.Property property) {
        getJavaFile().getPropertyList().add(property);

        String prefix = property.getName().substring(0, 1).toUpperCase();
        String suffix = property.getName().substring(1);

        if (property.isUseGet()) {
            JavaFile.Method method = new JavaFile.Method(property.getComment(), "public", property.getType(), "get" + prefix + suffix);
            method.getContent().add(code("return this." + property.getName()));
            getJavaFile().getMethodList().add(method);
        }
        if (property.isUseGet()) {
            JavaFile.Method method = new JavaFile.Method(property.getComment(), "public", "set" + prefix + suffix);
            method.getParameterList().add(property.getType() + " " + property.getName());
            method.getContent().add(code("this." + property.getName() + " = " + property.getName()));
            getJavaFile().getMethodList().add(method);
        }
    }

    /**
     * 添加数据表中对应的属性
     *
     * @param schemaField
     */
    protected void addProperty(SchemaField schemaField) {
        JavaFile.Property prop = new JavaFile.Property();
        //prop.setUseGet(true);
        //prop.setUseSet(true);
        prop.setName(schemaField.getProperty());
        prop.setType(schemaField.getPropertyType().getName());
        JavaFile.Comment comment = new JavaFile.Comment(schemaField.getComment());
        prop.setComment(comment.toComment());
        addImport(schemaField.getPropertyType().getFullName());
        addProperty(prop);
    }

    /**
     * 添加spring bean 注入对象
     *
     * @param serviceFullType
     */
    protected void addPropertyForAutowired(String serviceFullType) {
        int i = serviceFullType.lastIndexOf(".") + 1;
        String type = serviceFullType.substring(i, serviceFullType.length());
        String prefix = type.substring(0, 1).toLowerCase();
        String suffix = type.substring(1);
        JavaFile.Property prop = new JavaFile.Property();
        prop.setName(prefix + suffix);
        prop.setType(type);
        prop.getAnnotationList().add("@Autowired");
        addImport("org.springframework.beans.factory.annotation.Autowired");
        addImport(serviceFullType);
        addProperty(prop);
    }

    /**
     * 添加方法
     *
     * @param method
     */
    protected void addMethod(JavaFile.Method method) {
        getJavaFile().getMethodList().add(method);
    }

    /**
     * 生成一行java代码
     *
     * @param row
     * @return
     */
    protected String code(String row) {
        return new JavaFile.CodeRow(row).toString();
    }

    /**
     * 获取模型Type
     *
     * @param schema
     * @return
     */
    protected String getModelType(Schema schema) {
        return schema.getModule().getEntity();
    }

    /**
     * 获取模型Class
     *
     * @param schema
     * @return
     */
    protected String getModelFullType(Schema schema) {
        return schema.getModule().getClassName();
    }

    /**
     * 获取mapper Type
     *
     * @param schema
     * @return
     */
    protected String getMapperType(Schema schema) {
        return schema.getModule().getEntity() + "Mapper";
    }

    /**
     * 获取Mapper名称
     *
     * @param schema
     * @return
     */
    protected String getMapperName(Schema schema) {
        return StringUtils.transformCamel(getMapperType(schema));
    }

    /**
     * 获取Mapper Full Type
     *
     * @param schema
     * @return
     */
    protected String getMapperFullType(Schema schema) {
        String rs = schema.getModule().getPackageName() + ".dao." + getMapperType(schema);
        return rs;
    }

    /**
     * 获取Service名称
     *
     * @param schema
     * @return
     */
    protected String getServiceName(Schema schema) {
        String rs = schema.getModule().getEntity() + "Service";
        return StringUtils.transformCamel(rs);
    }

    protected String getServiceType(Schema schema) {
        return schema.getModule().getEntity() + "Service";
    }

    protected String getServiceFullType(Schema schema) {
        return schema.getModule().getPackageName() + ".service." + getServiceType(schema);
    }

    protected String getCallMethodParams(Schema schema) {
        final StringBuilder sb = new StringBuilder();
        schema.getPKList().forEach((key) -> {
            sb.append(key.getProperty() + ", ");
        });
        String rs = sb.toString();
        return rs.substring(0, rs.length() - 2);
    }

    protected String getCallMethod(Schema schema, String methodName) {
        return methodName + "(" + getCallMethodParams(schema) + ")";
    }

    protected String getCall(String handler, Schema schema, String methodName) {
        return handler + "." + getCallMethod(schema, methodName);
    }

    protected String getReturnCall(String handler, Schema schema, String methodName) {
        return "return " + getCall(handler, schema, methodName) + ";";
    }

    protected List<String> getPackageModel(Schema schema) {
        List<String> rs = new ArrayList<>();
        String modelType = getModelType(schema);
        rs.add("\t" + modelType + " entity = " + "new " + modelType + "();");
        schema.getPKList().forEach((key) -> {
            String property = key.getProperty();
            String prefix = property.substring(0, 1).toUpperCase();
            String suffix = property.substring(1);
            rs.add("\tentity.set" + prefix + suffix + "(" + property + ");");
        });
        return rs;
    }

    protected List<String> getReturnPackageModel(String handler, Schema schema, String methodName) {
        List<String> packageModel = getPackageModel(schema);
        packageModel.add("\treturn " + handler + "." + methodName + "(entity);");
        return packageModel;
    }

    protected List<String> getReturnDefault(String handler, Schema schema, String methodName) {
        List<String> packageModel = new ArrayList<>();
        packageModel.add("\treturn " + handler + "." + methodName + "(entity);");
        return packageModel;
    }

    protected String getExceptionDefault(Config config, String msg, String e) {
        String ex = config.getShortServiceException();
        return "\tthrow new " + ex + "(ExceptionCode.SYSTEM_ERROR.getCode(), \"" + msg + "\", " + e + ");";
    }

    protected List<String> getTryCatch(Config config, Schema schema, String handler, String methodName, InsertCode callback) {
        String msg = getModelType(schema) + " " + methodName + " has error";
        List<String> codeList = new ArrayList<>();
        codeList.add("try{");
        List<String> innerCode = callback.exec(config, schema, handler, methodName);
        codeList.addAll(innerCode);
        codeList.add("} catch (Exception e) {");
        codeList.add(getExceptionDefault(config, msg, "e"));
        codeList.add("}");
        return codeList;
    }

    protected List<String> getTryCatch(Config config, Schema schema, String handler, String methodName, InsertCode callback, String padding) {
        List<String> rs = getTryCatch(config, schema, handler, methodName, callback);
        List<String> result = new ArrayList<>();
        rs.forEach((item) -> {
            item = padding + item;
            result.add(item);
        });
        return result;
    }

    protected String getTransaction(Config config, Schema schema) {
        addImport("org.springframework.transaction.annotation.Transactional");
        addImport(getServiceFullType(schema));
        return "@Transactional(rollbackFor = " + config.getShortServiceException() + ".class" + ")";
    }

    public static interface InsertCode {
        List<String> exec(Config config, Schema schema, String handler, String methodName);
    }
}
