package com.scsxyz.java.generator.codegen.xml;

import com.scsxyz.java.generator.codegen.AbstractGenerator;
import com.scsxyz.java.generator.config.Config;
import com.scsxyz.java.generator.util.Constants;
import com.scsxyz.java.generator.util.FreemarkerUtils;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public abstract class SQLGenerator extends AbstractGenerator {

    public static final Logger LOGGER = LoggerFactory.getLogger(SQLGenerator.class);

    protected String generateByFreeMarker(Config config, Schema schema, String tpl) {
        Map<String, Object> root = new HashMap();
        root.put(Constants.ROOT_KEY, schema);
        root.put("config", config);
        try {
            return FreemarkerUtils.generate(tpl, root);
        } catch (IOException e) {
            LOGGER.error("Generate SQL IO", e);
            return null;
        } catch (TemplateException e) {
            LOGGER.error("Generate SQL TPL", e);
            return null;
        }
    }
}
