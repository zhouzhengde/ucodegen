package com.scsxyz.java.generator.codegen.java.impl;

import com.scsxyz.java.generator.codegen.java.JavaGenerator;
import com.scsxyz.java.generator.codegen.java.standard.JavaFile;
import com.scsxyz.java.generator.codegen.xml.Schema;
import com.scsxyz.java.generator.config.Config;
import com.scsxyz.java.generator.config.Module;
import com.scsxyz.java.generator.util.Constants;

/**
 * Created by Bond(China) on 2017/11/4.
 */
public class MapperJavaGenerator extends JavaGenerator {

    @Override
    public String generateInsert(Config config, Schema schema) {
        // 配置方法
        JavaFile.Parameter parameter = new JavaFile.Parameter(schema.getModule().getEntity(), "entity");

        JavaFile.Method method = new JavaFile.Method();
        method.setReturnType("Integer");
        method.setName("insert");
        method.setAbstract(true);
        method.getParameterList().add(parameter.toString());
        addImport(schema.getModule().getClassName());
        //配置注释
        JavaFile.Comment comment = new JavaFile.Comment("新增：" + schema.getModule().getEntity() + "，数据表：" + schema.getSchemaName());
        setMethodComment(method, comment);
        addMethod(method);
        return null;
    }

    @Override
    public String generateUpdate(Config config, Schema schema) {
        // 配置方法
        JavaFile.Parameter parameter = new JavaFile.Parameter(schema.getModule().getEntity(), "entity");

        JavaFile.Method method = new JavaFile.Method();
        method.setReturnType("Integer");
        method.setName("updateByPK");
        method.setAbstract(true);
        method.getParameterList().add(parameter.toString());
        addImport(schema.getModule().getClassName());
        //配置注释
        JavaFile.Comment comment = new JavaFile.Comment("修改：" + schema.getModule().getEntity() + "，数据表：" + schema.getSchemaName());
        setMethodComment(method, comment);
        addMethod(method);
        return null;
    }

    @Override
    public String generateFindByPK(Config config, Schema schema) {
        JavaFile.Parameter parameter = new JavaFile.Parameter(schema.getModule().getEntity(), "entity");
        // 配置方法
        final JavaFile.Method method = new JavaFile.Method();
        method.setReturnType(schema.getModule().getEntity());
        method.setName("findByPK");
        method.setAbstract(true);
        method.getParameterList().add(parameter.toString());
        addImport(schema.getModule().getClassName());
        //配置注释
        JavaFile.Comment comment = new JavaFile.Comment("通过主键查询一个：" + schema.getModule().getEntity() + "，数据表：" + schema.getSchemaName());
        setMethodComment(method, comment);
        addMethod(method);
        return null;
    }

    @Override
    public String generateFind(Config config, Schema schema) {

        JavaFile.Parameter parameter = new JavaFile.Parameter(schema.getModule().getEntity(), "entity");
        // 配置方法
        final JavaFile.Method method = new JavaFile.Method();
        method.setReturnType("List<" + schema.getModule().getEntity() + ">");
        method.setName("find");
        method.setAbstract(true);
        method.getParameterList().add(parameter.toString());
        addImport("java.util.List");
        addImport(schema.getModule().getClassName());
        //配置注释
        JavaFile.Comment comment = new JavaFile.Comment("查询列表：" + schema.getModule().getEntity() + "，数据表：" + schema.getSchemaName());
        setMethodComment(method, comment);
        addMethod(method);
        return null;
    }

    @Override
    public String generateDeleteByPK(Config config, Schema schema) {
        JavaFile.Parameter parameter = new JavaFile.Parameter(schema.getModule().getEntity(), "entity");

        // 配置方法
        final JavaFile.Method method = new JavaFile.Method();
        method.setReturnType("Integer");
        method.setName("deleteByPK");
        method.setAbstract(true);
        method.getParameterList().add(parameter.toString());
        addImport(schema.getModule().getClassName());
        //配置注释
        JavaFile.Comment comment = new JavaFile.Comment("删除：" + schema.getModule().getEntity() + "，数据表：" + schema.getSchemaName());
        setMethodComment(method, comment);
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
        javaFile.setInterface(true);
        javaFile.setPackageName(module.getPackageName() + ".dao");
        javaFile.setClassName(module.getEntity() + "Mapper");
        addClassComment(new JavaFile.Comment(module.getEntity() + "数据访问层"));
    }
}
