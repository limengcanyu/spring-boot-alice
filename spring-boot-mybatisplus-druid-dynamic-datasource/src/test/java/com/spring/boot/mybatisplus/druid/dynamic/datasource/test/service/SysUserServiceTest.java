package com.spring.boot.mybatisplus.druid.dynamic.datasource.test.service;

import com.spring.boot.mybatisplus.druid.dynamic.datasource.DruidDynamicDatasourceApplication;
import com.spring.boot.mybatisplus.druid.dynamic.datasource.entity.SysUser;
import com.spring.boot.mybatisplus.druid.dynamic.datasource.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DruidDynamicDatasourceApplication.class)
public class SysUserServiceTest {
    @Autowired
    private SysUserService sysUserService;

    @Test
    public void test() {
        SysUser sysUser = sysUserService.getById(1);
        System.out.println(sysUser);

    }

}
