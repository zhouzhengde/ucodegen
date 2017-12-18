package com.scsxyz.java.generator.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Jdbc 工具类
 * Created by Bond(China) on 2017/9/7.
 */
public final class DbUtils {

    public static final Logger LOGGER = Logger.getLogger(DbUtils.class.getName());

    /**
     * 获取连接
     *
     * @param driverClass
     * @param jdbcUrl
     * @return
     */
    public static Connection getConnection(String driverClass, String jdbcUrl, String userName, String password) throws SQLException {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.WARNING, "Load Driver Class", e);
            return null;
        }
        return DriverManager.getConnection(jdbcUrl, userName, password);
    }

    /**
     * 关闭连接
     *
     * @param con
     */
    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Close Connection", e);
            }
        }
    }
}
