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
 * Date 2019/12/03 19:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ITenantUserServiceTest {
    @Autowired
    private ITenantUserService tenantUserService;

    @Test
    public void test() {
        System.out.println(tenantUserService.getById(1));
    }
}
