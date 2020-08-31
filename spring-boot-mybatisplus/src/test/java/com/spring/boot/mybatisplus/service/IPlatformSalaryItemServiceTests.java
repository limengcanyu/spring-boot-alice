package com.spring.boot.mybatisplus.service;

import com.spring.boot.mybatisplus.dao.entity.PlatformSalaryItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IPlatformSalaryItemServiceTests {
    @Autowired
    private IPlatformSalaryItemService platformSalaryItemService;

    @Test
    public void test() {
        PlatformSalaryItem platformSalaryItem = platformSalaryItemService.getById(1);
        System.out.println(platformSalaryItem);
    }
}
