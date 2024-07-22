package com.ingsha.util;

/**
 * @ClassName CamelCaseToSnakeCase
 * @Description TODO
 * @Author Martin Yi
 * @Date 2024/7/22 23:31
 * @Version 1.0
 */
public class CamelCaseToSnakeCaseUtil {

    public static String convertCamelToSnake(String camelCase) {
        StringBuilder result = new StringBuilder();
        char[] chars = camelCase.toCharArray();

        for (char c : chars) {
            if (Character.isUpperCase(c)) {
                result
                        .append("_")
                        .append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

}
