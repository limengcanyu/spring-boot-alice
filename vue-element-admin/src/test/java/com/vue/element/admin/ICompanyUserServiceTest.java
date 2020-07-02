package com.vue.element.admin;

import com.vue.element.admin.mybatisplus.entity.CompanyUser;
import com.vue.element.admin.mybatisplus.service.ICompanyUserService;
import com.vue.element.admin.utils.ContextUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * description:
 *
 * @author rock
 * time 2020/7/2 0002 9:30
 */
@SpringBootTest
public class ICompanyUserServiceTest {
    @Autowired
    private ICompanyUserService companyUserService;

    @Test
    public void add() {
        ContextUtils.setTenantId("tenant_000001");

        CompanyUser companyUser = new CompanyUser();
        companyUser.setCompanyId("company_000001");
        companyUser.setUsername("admin");
        companyUser.setPassword("1234567890");
        companyUser.setUserId("user_000001");
        companyUserService.save(companyUser);
    }

}
