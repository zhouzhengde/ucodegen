package com.scsxyz.java.generator.codegen;

import com.scsxyz.java.generator.codegen.xml.Schema;
import com.scsxyz.java.generator.config.Config;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public interface IFileGenerator {

    void generate(Config config, Schema schema, String template);
}
