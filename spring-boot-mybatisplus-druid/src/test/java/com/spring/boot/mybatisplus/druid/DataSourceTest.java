package com.spring.boot.mybatisplus.druid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/5/23 0023
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DruidApplication.class)
public class DataSourceTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void test(){
        try {
            System.out.println("DataSource: " + dataSource);
            System.out.println("DataSource Class: " + dataSource.getClass());
            System.out.println("DataSource Connection: " + dataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
