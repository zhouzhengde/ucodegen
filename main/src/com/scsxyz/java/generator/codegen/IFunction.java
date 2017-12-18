package com.scsxyz.java.generator.codegen;

import com.scsxyz.java.generator.codegen.xml.Schema;
import com.scsxyz.java.generator.config.Config;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public interface IFunction {

    String generateInsert(Config config, Schema schema);

    String generateUpdate(Config config, Schema schema);

    String generateFindByPK(Config config, Schema schema);

    String generateFind(Config config, Schema schema);

    String generateDeleteByPK(Config config, Schema schema);
}
