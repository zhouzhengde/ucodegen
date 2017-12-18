package com.scsxyz.java.generator.codegen.java.impl;

import com.scsxyz.java.generator.codegen.java.JavaGenerator;
import com.scsxyz.java.generator.codegen.xml.Schema;
import com.scsxyz.java.generator.config.Config;
import com.scsxyz.java.generator.config.Module;
import com.scsxyz.java.generator.util.Constants;
import com.scsxyz.java.generator.codegen.java.standard.JavaFile;

/**
 * Created by Bond(China) on 2017/11/4.
 */
public class EntityJavaGenerator extends JavaGenerator {

    @Override
    public String generateInsert(Config config, Schema schema) {
        return null;
    }

    @Override
    public String generateUpdate(Config config, Schema schema) {
        return null;
    }

    @Override
    public String generateFindByPK(Config config, Schema schema) {
        return null;
    }

    @Override
    public String generateFind(Config config, Schema schema) {
        return null;
    }

    @Override
    public String generateDeleteByPK(Config config, Schema schema) {
        return null;
    }

    @Override
    protected String getOutPath(String workDir, Schema schema) {
        return workDir + Constants.MAIN_JAVA_DIR + getJavaFileName();
    }

    @Override
    public void configure(Config config, Schema schema) {
        setConfig(config);
        JavaFile javaFile = getJavaFile();
        javaFile.setAuthor(config.getAuthor());
        Module module = schema.getModule();
        javaFile.setPackageName(module.getPackageName() + ".entity");
        javaFile.setPackageName("lombok.Data");
        javaFile.setClassName(module.getEntity());
        javaFile.getAnnotationList().add("@Data");

        addClassComment(new JavaFile.Comment(module.getEntity() + "实体类"));
        addExtends(config.getBaseEntity());
        schema.getFieldList().forEach((item) -> {
            addProperty(item);
        });
    }
}
