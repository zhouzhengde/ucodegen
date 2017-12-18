package com.scsxyz.java.generator.codegen.java.impl;

import com.scsxyz.java.generator.codegen.java.JavaGenerator;
import com.scsxyz.java.generator.codegen.java.standard.JavaFile;
import com.scsxyz.java.generator.codegen.xml.Schema;
import com.scsxyz.java.generator.config.Config;
import com.scsxyz.java.generator.config.Module;
import com.scsxyz.java.generator.util.Constants;
import com.scsxyz.java.generator.util.StringUtils;


/**
 * Created by Bond(China) on 2017/11/4.
 */
public class ControllerJavaGenerator extends JavaGenerator {

    @Override
    public String generateInsert(Config config, Schema schema) {
        // 配置方法
        JavaFile.Parameter parameter = new JavaFile.Parameter(schema.getModule().getEntity(), "entity");
        JavaFile.Method method = new JavaFile.Method();
        method.setPublicType("public");
        method.setReturnType("Map<String, Object>");
        method.setName("insert");
        method.setAbstract(false);
        method.getParameterList().add(parameter.toString());
        method.getThrowsList().add(config.getShortServiceException());
        method.getAnnotationList().add("@RequestMapping(method = RequestMethod.POST)");
        method.getContent().add("return success(" + getServiceName(schema) + "." + method.getName() + "(entity));");
        addMethod(method);
        return null;
    }

    @Override
    public String generateUpdate(Config config, Schema schema) {
        // 配置方法
        JavaFile.Parameter parameter = new JavaFile.Parameter(schema.getModule().getEntity(), "entity");
        JavaFile.Method method = new JavaFile.Method();
        method.setPublicType("public");
        method.setReturnType("Map<String, Object>");
        method.setName("updateByPK");
        method.setAbstract(false);
        method.getParameterList().add("@RequestBody " + parameter.toString());
        method.getThrowsList().add(config.getShortServiceException());
        method.getAnnotationList().add("@RequestMapping(method = RequestMethod.PUT)");
        method.getContent().add("return success(" + getServiceName(schema) + "." + method.getName() + "(entity));");
        addMethod(method);
        return null;
    }

    @Override
    public String generateFindByPK(Config config, Schema schema) {
        // 配置方法
        JavaFile.Method method = new JavaFile.Method();
        method.setPublicType("public");
        method.setReturnType("Map<String, Object>");
        method.setName("findByPK");
        method.setAbstract(false);

        schema.getPKList().forEach((key) -> {
            method.getParameterList().add("@PathVariable(\"" + key.getProperty() + "\") " + key.getPropertyType().getName() + " " + key.getProperty());
            addImport(key.getPropertyType().getFullName());
        });

        method.getThrowsList().add(config.getShortServiceException());
        method.getAnnotationList().add("@RequestMapping(value=\"{id}\", method = RequestMethod.GET)");
        method.getContent().add("return success(" + getCall(getServiceName(schema), schema, method.getName()) + ");");
        addMethod(method);
        return null;
    }

    @Override
    public String generateFind(Config config, Schema schema) {

        JavaFile.Parameter parameter = new JavaFile.Parameter("Pager<" + schema.getModule().getEntity() + ">", "pager");
        // 配置方法
        final JavaFile.Method method = new JavaFile.Method();
        method.setPublicType("public");
        method.setReturnType("Map<String, Object>");
        method.setName("find");
        method.setAbstract(false);

        method.getParameterList().add("@RequestBody " + parameter.toString());
        method.getThrowsList().add(config.getShortServiceException());

        method.getAnnotationList().add("@RequestMapping(value=\"list\",method = RequestMethod.POST)");
        method.getContent().add("return success(" + getServiceName(schema) + "."  + method.getName() + "(pager)" + ");");
        addMethod(method);
        return null;
    }

    @Override
    public String generateDeleteByPK(Config config, Schema schema) {

        // 配置方法
        JavaFile.Method method = new JavaFile.Method();
        method.setPublicType("public");
        method.setReturnType("Map<String, Object>");
        method.setName("deleteByPK");
        method.setAbstract(false);

        schema.getPKList().forEach((key) -> {
            method.getParameterList().add("@PathVariable(\"" + key.getProperty() + "\") " + key.getPropertyType().getName() + " " + key.getProperty());
            addImport(key.getPropertyType().getFullName());
        });

        method.getThrowsList().add(config.getShortServiceException());
        method.getAnnotationList().add("@RequestMapping(value=\"{id}\", method = RequestMethod.DELETE)");
        method.getContent().add("return success(" + getCall(getServiceName(schema), schema, method.getName()) + ");");
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
        javaFile.setPackageName(module.getPackageName() + ".controller");
        javaFile.setClassName(module.getEntity() + "Controller");
        javaFile.getAnnotationList().add("@RestController");
        javaFile.getAnnotationList().add("@RequestMapping(\"api/cors/" + StringUtils.transformCamel(module.getEntity()) + "\")");

        javaFile.setExtendsName("BaseController");

        JavaFile.Property property = new JavaFile.Property();
        property.getAnnotationList().add("@Autowired");
        property.setType(getServiceType(schema));
        property.setName(getServiceName(schema));
        addProperty(property);
        addClassComment(new JavaFile.Comment(module.getEntity() + "业务接口实现层"));

        addImport(getServiceFullType(schema));
        addImport(getModelFullType(schema));
        addImport(config.getServiceException());
        addImport(config.getPager());
        addImport(config.getBaseController());

        addImport("java.util.Map");
        addImport("org.springframework.beans.factory.annotation.Autowired");
        addImport("org.springframework.web.bind.annotation.*");

    }
}
