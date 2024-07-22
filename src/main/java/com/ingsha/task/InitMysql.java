package com.ingsha.task;

import com.ingsha.util.MySQLConnectUtil;
import com.ingsha.util.MysqlPoolUtil;
import com.ingsha.util.SqlPoolStorage;
import io.quarkus.runtime.Shutdown;
import io.quarkus.runtime.Startup;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.sqlclient.PoolOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * @ClassName InitMysql
 * @Description TODO
 * @Author Martin Yi
 * @Date 2024/7/22 23:24
 * @Version 1.0
 */

@ApplicationScoped
public class InitMysql {


    @Inject
    Vertx vertx;

    @Startup
    void init() {
        PoolOptions poolOptions = MysqlPoolUtil.getPoolOptions();
        MySQLConnectOptions mySQLConnectOptions = MySQLConnectUtil.getMySQLConnectOptions("192.168.43.138", "ingsha_test_user", "root", "root", 3306);
        MySQLPool pool = MySQLPool.pool(vertx, mySQLConnectOptions, poolOptions);
        SqlPoolStorage.put(10001L, pool);
        System.out.println("sql init");
    }

    @Shutdown
    void destroy() {
        SqlPoolStorage.destroy();
    }


}
