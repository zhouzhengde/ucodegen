package com.scsxyz.java.generator.codegen.java;

import com.scsxyz.java.generator.config.Config;
import com.scsxyz.java.generator.util.Constants;
import com.scsxyz.java.generator.util.FileUtils;
import com.scsxyz.java.generator.util.FreemarkerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Bond(China) on 2017/11/5.
 */
public final class PomGenerator {

    private PomGenerator() {
    }

    public static final Logger LOGGER = LoggerFactory.getLogger(PomGenerator.class);

    public static void generate(Config config, String template) {

        String fileName = config.getWorkDir() + "pom.xml";

        Map<String, Object> root = new HashMap();
        root.put(Constants.ROOT_KEY, config);
        // 生成文件
        try {
            String rs = FreemarkerUtils.generate(template, root);
            LOGGER.info("Generate File:" + fileName);
            FileUtils.write(rs, new File(fileName));
        } catch (Exception e) {
            LOGGER.error("Generate File", e);
        }
    }
}
