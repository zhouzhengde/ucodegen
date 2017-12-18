package com.scsxyz.java.generator.util;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public class DbUtilsTest {

    @Test
    public void getConnection() throws Exception {
        String jdbcUrl = "jdbc:mysql://172.29.9.52:3306/mypt?useUnicode=true&characterEncoding=utf-8";
        String driverClass = "com.mysql.jdbc.Driver";
        String userName = "mypt";
        String password = "mypt0001";
        Connection connection = DbUtils.getConnection(driverClass, jdbcUrl, userName, password);
        Assert.assertNotNull(connection);
        DbUtils.close(connection);
    }
}