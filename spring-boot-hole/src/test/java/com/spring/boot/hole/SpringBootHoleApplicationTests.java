package com.spring.boot.hole;

import com.spring.boot.hole.dao.entity.ExampleTable;
import com.spring.boot.hole.service.ExampleTableService;
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
    void getList() {
        List<ExampleTable> list = exampleTableService.getListByColumn1Name("1");
        System.out.println(list);
    }

}
