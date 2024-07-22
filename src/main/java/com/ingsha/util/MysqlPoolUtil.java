package com.ingsha.util;

import io.vertx.sqlclient.PoolOptions;

/**
 * @ClassName MysqlPoolUtil
 * @Description TODO
 * @Author Martin Yi
 * @Date 2024/4/25 13:52
 * @Version 1.0
 */
public class MysqlPoolUtil {

    public static PoolOptions getPoolOptions() {
        PoolOptions poolOptions = new PoolOptions();
        return poolOptions;
    }


}
