package com.spring.boot.mybatisplus.utils;

import com.google.common.base.CaseFormat;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * @date 2021/4/27 11:21
 */
public class GuavaUtils {

    /**
     * 驼峰转下划线
     *
     * @param column
     * @return
     */
    public static String camel2Underline(String column) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, column);
    }

    public static String underline2Camel(String column) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, column);
    }
}
