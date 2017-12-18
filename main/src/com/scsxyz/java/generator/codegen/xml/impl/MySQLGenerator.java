package com.scsxyz.java.generator.codegen.xml.impl;

import com.scsxyz.java.generator.codegen.xml.SQLGenerator;
import com.scsxyz.java.generator.codegen.xml.Schema;
import com.scsxyz.java.generator.config.Config;
import com.scsxyz.java.generator.util.Constants;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public class MySQLGenerator extends SQLGenerator {

    @Override
    public String generateInsert(Config config, Schema schema) {
        return super.generateByFreeMarker(config, schema, Constants.SQL_INSERT_TPL);
    }

    @Override
    public String generateUpdate(Config config, Schema schema) {
        return super.generateByFreeMarker(config, schema, Constants.SQL_UPDATE_TPL);
    }

    @Override
    public String generateFindByPK(Config config, Schema schema) {
        return generateByFreeMarker(config, schema, Constants.SQL_FIND_BY_PK_TPL);
    }

    @Override
    public String generateFind(Config config, Schema schema) {
        return generateByFreeMarker(config, schema, Constants.SQL_FIND_TPL);
    }

    @Override
    public String generateDeleteByPK(Config config, Schema schema) {
        return super.generateByFreeMarker(config, schema, Constants.SQL_DELETE_TPL);
    }

    protected String getOutPath(String workDir, Schema schema) {
        return workDir + Constants.MAIN_RES_MAPPER_DIR + getMapperName(schema);
    }

    private String getMapperName(Schema schema) {
        return schema.getModule().getEntity() + "Mapper.xml";
    }
}
