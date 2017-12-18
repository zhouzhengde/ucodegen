package com.scsxyz.java.generator.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Bond(China) on 2016/11/24.
 */
public final class FreemarkerUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(FreemarkerUtils.class);

    private static Configuration cfg;

    /**
     * 静态初始化模板的主目录
     */
    static {
        cfg = new Configuration();
        try {
            String rootDir = FreemarkerUtils.class.getResource("/").getPath();
            LOGGER.info("[Template root path]:" + rootDir);
            cfg.setDirectoryForTemplateLoading(new File(rootDir));
            cfg.setDefaultEncoding(Constants.ENCODING);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        } catch (IOException e) {
            LOGGER.error("[Freemarker Utils Init]", e);
        }
    }

    /**
     * 工具类不准实例化
     */
    private FreemarkerUtils() {
    }

    /**
     * @param template
     * @param dataMap
     * @return
     */
    public static String generate(String template, Map<String, Object> dataMap) throws IOException, TemplateException {
        File tempFile = newTempFile();
        process(template, dataMap, tempFile);
        String result = FileUtils.read(tempFile);
        FileUtils.deleteFile(tempFile.getPath());
        return result;
    }

    /**
     * 将数据与模板合并，并输出到临时文件
     *
     * @param template
     * @param dataMap
     * @param tempFile
     * @throws IOException
     */
    private static void process(String template, Map<String, Object> dataMap, File tempFile) throws IOException, TemplateException {
        Writer writer = null;
        try {
            Template temp = cfg.getTemplate(template);
            writer = new OutputStreamWriter(new FileOutputStream(tempFile));
            temp.setOutputEncoding(Constants.ENCODING);
            temp.process(dataMap, writer);
        } catch (TemplateException e) {
            throw e;
        } finally {
            FileUtils.close(writer);
        }
    }

    /**
     * 创建一个临时文件
     *
     * @return
     * @throws IOException
     */
    private static File newTempFile() throws IOException {
        File tempFile = new File(System.getProperty("java.io.tmpdir") + UUID.randomUUID().toString() + ".tmp");
        tempFile.createNewFile();
        return tempFile;
    }


}
