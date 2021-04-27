package com.spring.boot.mybatisplus.service;

import com.google.common.base.CaseFormat;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * @date 2021/4/27 11:12
 */
class GuavaTests {

    @Test
    void camel2Underline() {
        String resultStr = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "studentName");
        System.out.println("  >>> 驼峰转下划线 : " + resultStr);
    }

    @Test
    void underline2Camel() {
        String resultStr = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "student_name");
        System.out.println("  >>> 驼峰转下划线 : " + resultStr);
    }
}
