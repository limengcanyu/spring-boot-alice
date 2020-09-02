package com.spring.boot.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.spring.boot.mybatisplus.dao.entity.PlatformSalaryItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
public class SpringBootMybatisPlusApplicationTests {
    @Autowired
    private IPlatformSalaryItemService platformSalaryItemService;

    @Test
    void list() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LambdaQueryWrapper<PlatformSalaryItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.le(PlatformSalaryItem::getCreateTime, LocalDateTime.parse("2020-04-01 00:00:00", formatter));
        List<PlatformSalaryItem> tOrderList = platformSalaryItemService.list(queryWrapper);
        for (PlatformSalaryItem tOrder : tOrderList) {
            System.out.println(tOrder);
        }
    }

}
