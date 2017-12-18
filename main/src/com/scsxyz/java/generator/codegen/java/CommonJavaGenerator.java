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
public final class CommonJavaGenerator {

    private CommonJavaGenerator() {
    }

    public static final Logger LOGGER = LoggerFactory.getLogger(CommonJavaGenerator.class);

    public static void generate(Config config, String className, String template) {
        String out = className.replace(".", "/");
        String clz = out.substring(out.lastIndexOf("/") + 1) + ".java";
        String fileName = config.getWorkDir() + Constants.MAIN_JAVA_DIR + out + ".java";

        Map<String, Object> root = new HashMap();
        root.put(Constants.ROOT_KEY, config);
        // 生成文件
        try {
            String rs = FreemarkerUtils.generate(template + clz, root);
            LOGGER.info("Generate File:" + fileName);
            FileUtils.write(rs, new File(fileName));
        } catch (Exception e) {
            LOGGER.error("Generate File", e);
        }
    }
}
