package com.spring.boot.flaw;

import com.spring.boot.flaw.dao.entity.ExampleTable;
import com.spring.boot.flaw.service.ExampleTableService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringBootHoleApplicationTests {

    @Resource
    private ExampleTableService exampleTableService;

    @Test
    void list() {
        List<ExampleTable> list = exampleTableService.list();
        System.out.println(list);
    }

    @Test
    void getListByUserId() {
        List<ExampleTable> list = exampleTableService.getListByUserId("1");
        System.out.println("1: " + list);

        list = exampleTableService.getListByUserId("%%1");
        System.out.println("2: " + list);

        list = exampleTableService.getListByUserId("%%1 and zh_name = 1");
        System.out.println("3: " + list);
    }

    @Test
    void getListByZhName() {
        List<ExampleTable> list = exampleTableService.getListByZhName("a");
        System.out.println("1: " + list);

//        List<ExampleTable> list = exampleTableService.getListByColumn1Name("%%1");

        list = exampleTableService.getListByZhName("%%a || zh_name = samuro");
        System.out.println("2: " + list);
    }

    @Test
    void getOneByZhName() {
        ExampleTable exampleTable = exampleTableService.getOneByZhName("samuro");
        System.out.println("1: " + exampleTable);

        exampleTable = exampleTableService.getOneByZhName("samuro || zh_name = artanis");
        System.out.println("2: " + exampleTable);
    }

}
