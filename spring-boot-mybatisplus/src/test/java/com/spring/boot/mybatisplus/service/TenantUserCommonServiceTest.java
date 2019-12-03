package com.spring.boot.mybatisplus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2019/12/03 19:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TenantUserCommonServiceTest {
    @Autowired
    private TenantUserCommonService tenantUserCommonService;

    /**
     * 会更新记录，事务无效
     */
    @Test
    public void method1() {
        try {
            tenantUserCommonService.method1("Alice");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 不会更新记录，事务有效
     */
    @Test
    public void method2() {
        try {
            tenantUserCommonService.method2("Samuro");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
