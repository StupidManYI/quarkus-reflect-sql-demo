package com.ingsha.util;

import io.vertx.mutiny.mysqlclient.MySQLPool;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DatasourceConfig
 * @Description TODO
 * @Author Martin Yi
 * @Date 2024/4/28 09:32
 * @Version 1.0
 */

public class SqlPoolStorage {

    private static final Logger LOGGER = Logger.getLogger(SqlPoolStorage.class);

    private static Map<Long, MySQLPool> mySQLPoolMap = new HashMap<>();


    public static void destroy() { //同步关闭sqlpool
        LOGGER.info("start destroy dynamic datasource");
        mySQLPoolMap
                .keySet()
                .forEach(x -> mySQLPoolMap
                        .get(x)
                        .closeAndAwait());
        LOGGER.info("finish destroy dynamic datasource");
    }

    public static void put(Long tenantId, MySQLPool mySQLPool) {
        mySQLPoolMap.put(tenantId, mySQLPool);
    }

    public static MySQLPool get(Long tenantId) {
        MySQLPool mySQLPool = mySQLPoolMap.get(tenantId);
        if (mySQLPool == null) {
            throw new NullPointerException();
        }
        return mySQLPool;
    }

}
