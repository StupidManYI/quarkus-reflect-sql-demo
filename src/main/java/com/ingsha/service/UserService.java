package com.ingsha.service;

import com.ingsha.entity.IdParam;
import com.ingsha.entity.User;
import com.ingsha.util.SqlUtil;
import com.ingsha.util.SqlPoolStorage;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * @ClassName UserSerivce
 * @Description TODO
 * @Author Martin Yi
 * @Date 2024/7/22 22:40
 * @Version 1.0
 */
@ApplicationScoped
public class UserService {


    public Uni<String> insert(User user) {
        MySQLPool mySQLPool = SqlPoolStorage.get(10001L);
        String sql = SqlUtil.buildInsertSQL(user, "user");
        return mySQLPool
                .preparedQuery(sql)
                .execute()
                .flatMap(resultRow -> {
                    return Uni
                            .createFrom()
                            .item(sql);
                });
    }

    public Uni<String> update(User user) {
        MySQLPool mySQLPool = SqlPoolStorage.get(10001L);
        String sql = SqlUtil.buildUpdateSQL(user, "user");
        return mySQLPool
                .preparedQuery(sql)
                .execute()
                .flatMap(resultRow -> {
                    return Uni
                            .createFrom()
                            .item(sql);
                });
    }

    public Uni<String> delete(IdParam param) {
        MySQLPool mySQLPool = SqlPoolStorage.get(10001L);
        String sql = SqlUtil.buildDeleteSQL(param.getId(), "user");
        return mySQLPool
                .preparedQuery(sql)
                .execute()
                .flatMap(resultRow -> {
                    return Uni
                            .createFrom()
                            .item(sql);
                });
    }

    public Uni<User> select(IdParam idParam) {
        MySQLPool mySQLPool = SqlPoolStorage.get(10001L);
        String sql = SqlUtil.buildSelectOneSQL(idParam.getId(), User.class, "user");
        System.out.println(sql);
        return mySQLPool
                .preparedQuery(sql)
                .execute()
                .flatMap(resultRow -> {
                    User user = SqlUtil.translateOne(resultRow, User.class);
                    return Uni
                            .createFrom()
                            .item(user);
                });
    }


}
