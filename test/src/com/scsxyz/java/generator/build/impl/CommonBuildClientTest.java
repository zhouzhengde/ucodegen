package com.scsxyz.java.generator.build.impl;

import com.scsxyz.java.generator.config.Config;
import com.scsxyz.java.generator.config.Database;
import com.scsxyz.java.generator.config.Module;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public class CommonBuildClientTest {

    CommonBuildClient commonBuildClient = new CommonBuildClient();


    @Test
    public void build() throws Exception {

        Config config = new Config();
        init(config);
        commonBuildClient.build(config);
    }

    private void init(Config config) {
        config.setBasePackage("com.ivxyz.um");
        Database database = new Database();
        database.setDialect("mysql");
        database.setDriver("com.mysql.jdbc.Driver");
        database.setUrl("jdbc:mysql://localhost:3306/huajun56?useUnicode=true&characterEncoding=utf-8");
        database.setUsername("root");
        database.setPassword("root");

        List<Module> moduleList = new ArrayList<>();
        Module module = new Module();
        module.setEntity("User");
        module.setFrontend(true);
        module.setTable("web_user");
        module.setPackageName("user");
        moduleList.add(module);

        config.setDatabase(database);
        config.setModuleList(moduleList);
        config.setAuthor("Bond Zhou(China)");

    }
}