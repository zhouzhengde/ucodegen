package com.scsxyz.java.generator.codegen.java.impl;

import com.scsxyz.java.generator.codegen.java.JavaGenerator;
import com.scsxyz.java.generator.codegen.java.standard.JavaFile;
import com.scsxyz.java.generator.codegen.xml.Schema;
import com.scsxyz.java.generator.config.Config;
import com.scsxyz.java.generator.config.Module;
import com.scsxyz.java.generator.util.Constants;

import java.util.List;

/**
 * Created by Bond(China) on 2017/11/4.
 */
public class ServiceImplJavaGenerator extends JavaGenerator {

    @Override
    public String generateInsert(Config config, Schema schema) {
        // 配置方法
        JavaFile.Parameter parameter = new JavaFile.Parameter(schema.getModule().getEntity(), "entity");
        JavaFile.Method method = new JavaFile.Method();
        method.setPublicType("public");
        method.setReturnType("Integer");
        method.setName("insert");
        method.setAbstract(false);
        method.getParameterList().add(parameter.toString());
        method.getThrowsList().add(config.getShortServiceException());
        method.getAnnotationList().add(getTransaction(config, schema));
        method.getContent().addAll(getTryCatch(config, schema, getMapperName(schema), method.getName(), new InsertCode() {
            @Override
            public List<String> exec(Config config, Schema schema, String handler, String methodName) {
                return getReturnDefault(handler, schema, methodName);
            }
        }));
        addMethod(method);
        return null;
    }

    @Override
    public String generateUpdate(Config config, Schema schema) {
        // 配置方法
        JavaFile.Parameter parameter = new JavaFile.Parameter(schema.getModule().getEntity(), "entity");
        JavaFile.Method method = new JavaFile.Method();
        method.setPublicType("public");
        method.setReturnType("Integer");
        method.setName("updateByPK");
        method.setAbstract(false);
        method.getParameterList().add(parameter.toString());
        method.getThrowsList().add(config.getShortServiceException());
        method.getAnnotationList().add(getTransaction(config, schema));

        method.getContent().addAll(getTryCatch(config, schema, getMapperName(schema), method.getName(), new InsertCode() {
            @Override
            public List<String> exec(Config config, Schema schema, String handler, String methodName) {
                return getReturnDefault(handler, schema, methodName);
            }
        }));

        addMethod(method);
        return null;
    }

    @Override
    public String generateFindByPK(Config config, Schema schema) {
        JavaFile.Parameter parameter = new JavaFile.Parameter(schema.getModule().getEntity(), "entity");
        // 配置方法
        final JavaFile.Method method = new JavaFile.Method();
        method.setPublicType("public");
        method.setReturnType(schema.getModule().getEntity());
        method.setName("findByPK");
        method.setAbstract(false);

        schema.getPKList().forEach((key) -> {
            method.getParameterList().add(key.getPropertyType().getName() + " " + key.getProperty());
            addImport(key.getPropertyType().getFullName());
        });
        method.getThrowsList().add(config.getShortServiceException());
        method.getContent().addAll(getTryCatch(config, schema, getMapperName(schema), method.getName(), new InsertCode() {
            @Override
            public List<String> exec(Config config, Schema schema, String handler, String methodName) {
                return getReturnPackageModel(handler, schema, methodName);
            }
        }));
        addMethod(method);
        return null;
    }

    @Override
    public String generateFind(Config config, Schema schema) {

        JavaFile.Parameter parameter = new JavaFile.Parameter("Pager<" + schema.getModule().getEntity() + ">", "pager");
        // 配置方法
        final JavaFile.Method method = new JavaFile.Method();
        method.setPublicType("public");
        method.setReturnType("Pager<" + schema.getModule().getEntity() + ">");
        method.setName("find");
        method.setAbstract(false);
        method.getParameterList().add(parameter.toString());
        method.getThrowsList().add(config.getShortServiceException());
        method.getContent().add("return pager.query(new Pager.Callback<" + getModelType(schema) + ">() {");
        method.getContent().add("\tpublic List<" + getModelType(schema) + "> query(" + getModelType(schema) + " entity) throws " + config.getShortServiceException() + " {");
        method.getContent().addAll(getTryCatch(config, schema, getMapperName(schema), method.getName(), new InsertCode() {
            @Override
            public List<String> exec(Config config, Schema schema, String handler, String methodName) {
                return getReturnDefault(handler, schema, methodName);
            }
        }, "\t\t"));
        method.getContent().add("\t}");
        method.getContent().add("});");
        addMethod(method);
        return null;
    }

    @Override
    public String generateDeleteByPK(Config config, Schema schema) {

        // 配置方法
        final JavaFile.Method method = new JavaFile.Method();
        method.setPublicType("public");
        method.setReturnType("Integer");
        method.setName("deleteByPK");
        method.setAbstract(false);
        schema.getPKList().forEach((key) -> {
            method.getParameterList().add(key.getPropertyType().getName() + " " + key.getProperty());
            addImport(key.getPropertyType().getFullName());
        });
        method.getThrowsList().add(config.getShortServiceException());
        method.getContent().addAll(getTryCatch(config, schema, getMapperName(schema), method.getName(), new InsertCode() {
            @Override
            public List<String> exec(Config config, Schema schema, String handler, String methodName) {
                return getReturnPackageModel(handler, schema, methodName);
            }
        }));
        addMethod(method);
        return null;
    }

    @Override
    protected String getOutPath(String workDir, Schema schema) {
        return workDir + Constants.MAIN_JAVA_DIR + getJavaFileName();
    }

    @Override
    public void configure(Config config, Schema schema) {
        Module module = schema.getModule();
        javaFile.setInterface(false);
        javaFile.setPackageName(module.getPackageName() + ".service.impl");
        javaFile.setClassName(module.getEntity() + "ServiceImpl");
        javaFile.getAnnotationList().add("@Service");
        javaFile.getInterfaceList().add(module.getEntity() + "Service");

        JavaFile.Property property = new JavaFile.Property();
        property.getAnnotationList().add("@Autowired");
        property.setType(getMapperType(schema));
        property.setName(getMapperName(schema));
        addProperty(property);
        addClassComment(new JavaFile.Comment(module.getEntity() + "业务接口实际层"));

        addImport(getMapperFullType(schema));
        addImport(getServiceFullType(schema));
        addImport(getModelFullType(schema));
        addImport(config.getServiceException());
        addImport(config.getPager());
        addImport(config.getExceptionCode());

        addImport("java.util.List");
        addImport("org.springframework.beans.factory.annotation.Autowired");
        addImport("org.springframework.stereotype.Service");
    }
}
