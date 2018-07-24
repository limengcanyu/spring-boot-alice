package com.spring.boot.mybatisplus.sharding.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/5/25 0025
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingJdbcApplication.class)
public class DataSourceTest {
    @Resource
    private DataSource dataSource;

    @Test
    public void test() {
        System.out.println("dataSource：" + dataSource);
    }
}
