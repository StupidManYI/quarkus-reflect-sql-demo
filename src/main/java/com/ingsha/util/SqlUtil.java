package com.ingsha.util;

import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Iterator;

/**
 * @ClassName GenerateSqlUtil
 * @Description TODO
 * @Author Martin Yi
 * @Date 2024/7/22 22:52
 * @Version 1.0
 */
public class SqlUtil {

    public static String buildInsertSQL(Object obj, String tableName) {
        Class<?> clazz = obj.getClass();
        StringBuilder sql = new StringBuilder("INSERT INTO ")
                .append(tableName)
                .append(" (");
        StringBuilder values = new StringBuilder(" VALUES (");
        boolean isFirstField = true;

        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = field.get(obj);
                if (fieldValue == null) {
                    continue;
                }
                if (!isFirstField) {
                    sql.append(", ");
                    values.append(", ");
                }
                sql.append(CamelCaseToSnakeCaseUtil.convertCamelToSnake(fieldName));
                values.append(formatValue(fieldValue));
                isFirstField = false;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        sql
                .append(")")
                .append(values)
                .append(")");
        return sql.toString();
    }

    public static String buildUpdateSQL(Object obj, String tableName) {
        Class<?> clazz = obj.getClass();
        StringBuilder sql = new StringBuilder("UPDATE ")
                .append(tableName)
                .append(" SET ");
        StringBuilder whereClause = new StringBuilder(" WHERE ");
        boolean isFirstField = true;

        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = field.get(obj);
                if (fieldValue == null) {
                    continue;
                }
                if ("id".equalsIgnoreCase(fieldName)) { // 假设 id 是主键
                    whereClause
                            .append(fieldName)
                            .append(" = ")
                            .append(formatValue(fieldValue));
                } else {
                    if (!isFirstField) {
                        sql.append(", ");
                    }
                    sql
                            .append(CamelCaseToSnakeCaseUtil.convertCamelToSnake(fieldName))
                            .append(" = ")
                            .append(formatValue(fieldValue));
                    isFirstField = false;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        sql.append(whereClause);
        return sql.toString();
    }

    public static String buildDeleteSQL(Long id, String tableName) {
        StringBuilder sql = new StringBuilder("DELETE FROM ")
                .append(tableName)
                .append(" WHERE ");
        if (id != null) { // 假设 id 是主键
            sql
                    .append("id")
                    .append(" = ")
                    .append(formatValue(id));
        }
        return sql.toString();
    }

    public static String buildSelectOneSQL(Long id, Class<?> clazz, String tableName) {
        if (id == null) {
            throw new NullPointerException();
        }
        StringBuilder sql = new StringBuilder("SELECT ");
        boolean isFirstField = true;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (!isFirstField) {
                sql.append(" , ");
            }
            sql
                    .append(CamelCaseToSnakeCaseUtil.convertCamelToSnake(fieldName));
            isFirstField = false;
        }
        sql
                .append(" FROM ")
                .append(tableName)
                .append(" WHERE ")
                .append("id")
                .append(" = ")
                .append(formatValue(id))
                .append(" limit 1 ");
        return sql.toString();
    }

    public static <T> T translateOne(RowSet<Row> rows, Class<T> clazz) {
        try {
            // Check if there is at least one row in the RowSet
            Iterator<Row> iterator = rows.iterator();
            if (!iterator.hasNext()) {
                return null;
            }
            Row row = iterator.next();

            // Create a new instance of the specified class
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true); // Allow access to private constructors
            T instance = constructor.newInstance();

            // Map each field of the class to the corresponding value from the Row
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true); // Allow access to private fields

                String fieldName = field.getName();
                Object value = row.getValue(CamelCaseToSnakeCaseUtil.convertCamelToSnake(fieldName)); // Get value from Row using field name

                if (value != null) {
                    // Set the value to the field of the instance
                    field.set(instance, value);
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Error translating RowSet to class", e);
        }
    }


    private static String formatValue(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }

}
