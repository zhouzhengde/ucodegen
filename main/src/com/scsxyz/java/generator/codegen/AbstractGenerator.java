package com.scsxyz.java.generator.codegen;

import com.scsxyz.java.generator.codegen.java.JavaGenerator;
import com.scsxyz.java.generator.codegen.xml.Schema;
import com.scsxyz.java.generator.config.Config;
import com.scsxyz.java.generator.util.Constants;
import com.scsxyz.java.generator.util.FileUtils;
import com.scsxyz.java.generator.util.FreemarkerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bond(China) on 2017/11/4.
 */
public abstract class AbstractGenerator implements IFunction, IFileGenerator {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenerator.class);

    private Config config;

    @Override
    public void generate(Config config, Schema schema, String template) {
        setConfig(config);
        schema.setUseLogicDel(isHasDel(schema));
        Map<String, Object> root = new HashMap();
        // 组织文件内容顺序
        List<String> list = new ArrayList<>();
        list.add(this.generateFindByPK(config, schema));
        list.add(this.generateFind(config, schema));
        list.add(this.generateInsert(config, schema));
        list.add(this.generateUpdate(config, schema));
        list.add(this.generateDeleteByPK(config, schema));

        // 设置数据Context
        root.put("schema", schema);
        root.put("config", config);

        if (this instanceof JavaGenerator) {
            JavaGenerator generator = (JavaGenerator) this;
            generator.configure(config, schema);
            root.put(Constants.ROOT_KEY, generator.getJavaFile());
        } else {
            root.put(Constants.ROOT_KEY, list);
        }

        // 生成文件
        try {
            String rs = FreemarkerUtils.generate(template, root);
            String fileName = getOutPath(config.getWorkDir(), schema);
            LOGGER.info("Generate File:" + fileName);
            FileUtils.write(rs, new File(fileName));
        } catch (Exception e) {
            LOGGER.error("Generate File", e);
        }
    }

    /**
     * 是否需要逻辑删除
     *
     * @param schema
     * @return
     */
    private boolean isHasDel(Schema schema) {
        boolean hasDel = false;
        for (int i = 0; i < schema.getFieldList().size(); i++) {
            hasDel = "is_del".equalsIgnoreCase(schema.getFieldList().get(i).getName());
            if (hasDel) {
                break;
            }
        }
        return hasDel;
    }

    /**
     * 设置输出路径
     *
     * @param workDir
     * @param schema
     * @return
     */
    protected abstract String getOutPath(String workDir, Schema schema);

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
