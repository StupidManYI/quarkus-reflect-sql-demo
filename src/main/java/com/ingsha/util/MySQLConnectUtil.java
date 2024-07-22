package com.ingsha.util;

import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.SslMode;

/**
 * @ClassName MySQLConnectUtil
 * @Description TODO
 * @Author Martin Yi
 * @Date 2024/4/25 13:54
 * @Version 1.0
 */
public class MySQLConnectUtil {

    public static MySQLConnectOptions getMySQLConnectOptions(String host, String database, String username, String password, Integer port) {
        MySQLConnectOptions c = new MySQLConnectOptions();
        c.setCharset(MySQLConnectOptions.DEFAULT_CHARSET);
        c.setHost(host);
        c.setDatabase(database);
        c.setUser(username);
        c.setPassword(password);
        c.setSslMode(SslMode.DISABLED);
        c.setSsl(false);
        c.setPort(port);
        c.setReconnectInterval(1000);
        c.setConnectTimeout(3000);
        c.setReconnectAttempts(10000);
        return c;
    }


}
