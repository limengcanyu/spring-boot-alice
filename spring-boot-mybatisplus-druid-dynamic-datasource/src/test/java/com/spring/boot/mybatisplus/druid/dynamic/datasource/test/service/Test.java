package com.spring.boot.mybatisplus.druid.dynamic.datasource.test.service;

import com.alibaba.fastjson.JSON;
import com.spring.boot.mybatisplus.druid.dynamic.datasource.action.DataBaseQuery;
import com.spring.boot.mybatisplus.druid.dynamic.datasource.action.DataBaseQueryContext;
import com.spring.boot.mybatisplus.druid.dynamic.datasource.config.ExtendSource;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @FileName: Test.java
 * @Description: Test.java类说明
 * @Author: tao.shi
 * @Date: 2019/3/6 14:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private DataBaseQueryContext dataBaseQueryContext;

    @org.junit.Test
    public void test() {
        ExtendSource extendSource = new ExtendSource();
        extendSource.setConnectionInfo("{\"userName\":\"root\",\"password\":\"Gaohang321\",\"jdbcUrl\":\"jdbc:mysql://192.168.31.128:3306/alita?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&characterEncoding=utf8\"}");
        extendSource.setDbType("MYSQL");
        extendSource.setId(1L);
        DataBaseQuery dataBaseQuery = dataBaseQueryContext.build(extendSource.getDbType());
        String sql = "select id, username, password from sys_user where id = 1;";
        Optional optional = dataBaseQuery.queryOradd(extendSource,sql);
        Assert.assertTrue(optional.isPresent());
        System.out.println(JSON.toJSONString(optional.get()));

        extendSource = new ExtendSource();
        extendSource.setConnectionInfo("{\"userName\":\"root\",\"password\":\"Gaohang321\",\"jdbcUrl\":\"jdbc:mysql://192.168.31.128:3306/samuro?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&characterEncoding=utf8\"}");
        extendSource.setDbType("MYSQL");
        extendSource.setId(1L);
        dataBaseQuery = dataBaseQueryContext.build(extendSource.getDbType());
        sql = "select id, username, password from sys_user where id = 1;";
        optional = dataBaseQuery.queryOradd(extendSource,sql);
        Assert.assertTrue(optional.isPresent());
        System.out.println(JSON.toJSONString(optional.get()));
    }

}
