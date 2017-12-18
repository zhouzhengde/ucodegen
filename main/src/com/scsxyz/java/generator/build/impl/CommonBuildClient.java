package com.scsxyz.java.generator.build.impl;

import com.scsxyz.java.generator.build.BuildClient;
import com.scsxyz.java.generator.codegen.java.CommonJavaGenerator;
import com.scsxyz.java.generator.codegen.java.ConfigGenerator;
import com.scsxyz.java.generator.codegen.java.PomGenerator;
import com.scsxyz.java.generator.codegen.java.impl.*;
import com.scsxyz.java.generator.codegen.xml.Dialect;
import com.scsxyz.java.generator.codegen.xml.Schema;
import com.scsxyz.java.generator.codegen.xml.SchemaField;
import com.scsxyz.java.generator.config.Config;
import com.scsxyz.java.generator.config.Database;
import com.scsxyz.java.generator.config.Module;
import com.scsxyz.java.generator.exception.ServiceException;
import com.scsxyz.java.generator.util.Constants;
import com.scsxyz.java.generator.util.DbUtils;
import com.scsxyz.java.generator.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Bond(China) on 2017/9/7.
 */
@Component("commonBuildClient")
public class CommonBuildClient implements BuildClient {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommonBuildClient.class);

    @Override
    public void build(Config config) throws ServiceException {

//        config.setWorkDir(System.getProperty("java.io.tmpdir") + UUID.randomUUID().toString() + "/");

        config.setWorkDir("/Users/bond/test/ucodegen-demo/");

        config.setBaseEntity(config.getBasePackage() + ".common.entity.BaseEntity");
        config.setPager(config.getBasePackage() + ".common.entity.Pager");
        config.setServiceException(config.getBasePackage() + ".common.exception.ServiceException");
        config.setExceptionCode(config.getBasePackage() + ".common.exception.ExceptionCode");
        config.setBaseController(config.getBasePackage() + ".common.base.BaseController");
        config.setApplicationRunner(config.getBasePackage() + ".ApplicationRunner");

        PomGenerator.generate(config, Constants.POM_TPL);
        ConfigGenerator.generate(config, Constants.APPLICATION_TPL);

        generatorTools(config);

        FileUtils.createDir(config.getWorkDir());
        LOGGER.info("Work Dir:" + config.getWorkDir());
        if (config.getModuleList() == null || config.getModuleList().size() == 0) {
            return;
        }
        List<Module> moduleList = config.getModuleList();
        List<Schema> schemaList = new ArrayList<>();
        try {
            readSchema(config, moduleList, schemaList);
        } catch (SQLException e) {
            LOGGER.error("Read Schema", e);
            throw new ServiceException("Read Schema", e);
        }
        generateCode(config, schemaList);
    }

    /**
     * 生成工具类
     *
     * @param config
     */
    private void generatorTools(Config config) {
        String basePackage = config.getBasePackage();

        CommonJavaGenerator.generate(config, config.getBaseEntity(), Constants.COMMON_TPL_DIR);
        CommonJavaGenerator.generate(config, config.getPager(), Constants.COMMON_TPL_DIR);
        CommonJavaGenerator.generate(config, config.getServiceException(), Constants.COMMON_TPL_DIR);
        CommonJavaGenerator.generate(config, config.getExceptionCode(), Constants.COMMON_TPL_DIR);
        CommonJavaGenerator.generate(config, config.getBaseController(), Constants.COMMON_TPL_DIR);
        CommonJavaGenerator.generate(config, config.getApplicationRunner(), Constants.COMMON_TPL_DIR);

        CommonJavaGenerator.generate(config, basePackage + ".common.util.Helper", Constants.COMMON_TPL_DIR);
        CommonJavaGenerator.generate(config, basePackage + ".common.util.Constants", Constants.COMMON_TPL_DIR);
        CommonJavaGenerator.generate(config, basePackage + ".common.util.DateUtils", Constants.COMMON_TPL_DIR);
        CommonJavaGenerator.generate(config, basePackage + ".common.entity.TreeBaseEntity", Constants.COMMON_TPL_DIR);
        CommonJavaGenerator.generate(config, basePackage + ".common.base.ResultMap", Constants.COMMON_TPL_DIR);

        CommonJavaGenerator.generate(config, basePackage + ".common.json.JsonUtils", Constants.COMMON_TPL_DIR);
        CommonJavaGenerator.generate(config, basePackage + ".common.json.annotation.JsonPathValue", Constants.COMMON_TPL_DIR);
        CommonJavaGenerator.generate(config, basePackage + ".common.util.BatchOperator", Constants.COMMON_TPL_DIR);
        CommonJavaGenerator.generate(config, basePackage + ".common.util.SHA1", Constants.COMMON_TPL_DIR);

        CommonJavaGenerator.generate(config, basePackage + ".common.config.DatasourceConfig", Constants.COMMON_TPL_DIR);
        CommonJavaGenerator.generate(config, basePackage + ".common.config.PageHelperProperties", Constants.COMMON_TPL_DIR);
        CommonJavaGenerator.generate(config, basePackage + ".common.base.Result", Constants.COMMON_TPL_DIR);
    }

    private void generateCode(Config config, List<Schema> schemaList) {
        Dialect dialect = Dialect.getByName(config.getDatabase().getDialect());
        schemaList.forEach((schema -> {
            dialect.getSqlGenerator().generate(config, schema, Constants.MAPPER_TPL);
            new EntityJavaGenerator().generate(config, schema, Constants.JAVA_TPL);
            new MapperJavaGenerator().generate(config, schema, Constants.JAVA_TPL);
            new ServiceJavaGenerator().generate(config, schema, Constants.JAVA_TPL);
            new ServiceImplJavaGenerator().generate(config, schema, Constants.JAVA_TPL);
            new ControllerJavaGenerator().generate(config, schema, Constants.JAVA_TPL);
        }));
    }

    /**
     * 获取数据表定义结构
     *
     * @param config
     * @param moduleList
     * @param schemaList
     */
    private void readSchema(Config config, List<Module> moduleList, final List<Schema> schemaList) throws SQLException {
        Database database = config.getDatabase();
        Connection connection = DbUtils.getConnection(database.getDriver(), database.getUrl(), database.getUsername(), database.getPassword());
        try {
            moduleList.forEach((module -> {
                Schema schema = new Schema();
                schema.setFieldList(getSchemaField(connection, config, module));
                schema.setModule(module);
                schema.setTypeName(module.getPackageName() + ".entity." + module.getEntity());
                schema.setSchemaName(module.getTable());
                if (schema.getFieldList() == null || schema.getFieldList().size() == 0) {
                    return;
                }
                schemaList.add(schema);
            }));
        } finally {
            DbUtils.close(connection);
        }
    }

    private List<SchemaField> getSchemaField(Connection connection, Config config, Module module) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<SchemaField> fieldList = null;
        try {
            fieldList = new ArrayList<SchemaField>();
            Dialect dialect = Dialect.getByName(config.getDatabase().getDialect());
            statement = connection.prepareStatement(dialect.getSchemaSQL());
            statement.setString(1, module.getTable());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                readResult(resultSet, fieldList);
            }
        } catch (SQLException e) {
            LOGGER.error("Build Code", e);
        } finally {
            close(statement, resultSet);
            return fieldList;
        }
    }

    /**
     * 读取Schema结果
     *
     * @param resultSet
     * @param fieldList
     * @throws SQLException
     */
    private void readResult(ResultSet resultSet, ArrayList<SchemaField> fieldList) throws SQLException {
        SchemaField schemaField = new SchemaField();
        schemaField.setName(resultSet.getString("Field"));
        schemaField.setType(resultSet.getString("Type"));
        schemaField.setKey(resultSet.getString("Key"));
        String comment = resultSet.getString("Comment");
        if (comment != null && !comment.isEmpty()) {
            schemaField.setComment(resultSet.getString("Comment"));
        }
        fieldList.add(schemaField);
    }

    /**
     * 关闭Db操作
     *
     * @param statement
     * @param resultSet
     */
    private void close(Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error("Close DB Opt", e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Close DB Opt", e);
            }
        }
    }
}
