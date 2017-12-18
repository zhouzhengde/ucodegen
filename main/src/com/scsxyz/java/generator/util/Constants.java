/*
 * Copyright (c) 2015. Bond(China)
 */

package com.scsxyz.java.generator.util;

/**
 * 常量类
 *
 * @author Bond(China)
 * @version 1.0.0
 */
public interface Constants {

    // ################ COMMON [START] #################
    String ENCODING = "UTF-8";
    String CONTENT_TYPE = "Content-Type";

    String MAIN_DIR = "main/";
    String MAIN_RES_DIR = MAIN_DIR + "resources/";
    String MAIN_RES_MAPPER_DIR = MAIN_RES_DIR + "mapper/";
    String MAIN_JAVA_DIR = MAIN_DIR + "java/";
    String MAIN_WEB_DIR = MAIN_DIR + "webapp/";
    String TEST_DIR = "test/";
    String TEST_RES_DIR = TEST_DIR + "resources/";
    String TEST_JAVA_DIR = TEST_DIR + "java/";

    String ROOT_KEY = "root";

    String MAPPER_TPL = "template/sql/mapper.tpl";
    String SQL_INSERT_TPL = "template/sql/insert.tpl";
    String SQL_UPDATE_TPL = "template/sql/update.tpl";
    String SQL_DELETE_TPL = "template/sql/delete.tpl";
    String SQL_FIND_BY_PK_TPL = "template/sql/find-by-pk.tpl";
    String SQL_FIND_TPL = "template/sql/find-list.tpl";

    String JAVA_TPL = "template/java/java.tpl";


    String COMMON_TPL_DIR = "template/java/base/";
    String POM_TPL = "template/java/pom.tpl";
    String APPLICATION_TPL = "template/java/application.yml";

    // ################ COMMON [END] ###################
}
