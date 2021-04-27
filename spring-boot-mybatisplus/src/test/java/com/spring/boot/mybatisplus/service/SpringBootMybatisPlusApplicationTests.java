package com.spring.boot.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.spring.boot.mybatisplus.dao.entity.PlatformSalaryItem;
import com.spring.boot.mybatisplus.dao.mapper.PlatformSalaryItemMapper;
import com.spring.boot.mybatisplus.utils.GuavaUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class SpringBootMybatisPlusApplicationTests {

    @Autowired
    private IPlatformSalaryItemService platformSalaryItemService;

    @Autowired
    private PlatformSalaryItemMapper platformSalaryItemMapper;

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

    @Test
    void addItem() {
        try {
            System.out.println("add item " + platformSalaryItemService.addItem());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void queryByColumns() {
        // 查询字段
        List<String> columnList = new ArrayList<>();
        columnList.add("itemCode");
        columnList.add("itemName");

        StringBuilder columns = new StringBuilder();
        columnList.forEach(column -> {
            columns.append(GuavaUtils.camel2Underline(column)).append(" ").append(column).append(",");
        });
        columns.deleteCharAt(columns.length() - 1);

        // 排序字段
        StringBuilder orders = new StringBuilder();
        columnList.forEach(column -> {
            orders.append(GuavaUtils.camel2Underline(column)).append(",");
        });
        orders.deleteCharAt(orders.length() - 1);

        String tableName = "platform_salary_item";
        List<Map<String, Object>> result = platformSalaryItemMapper.queryByColumns(String.join(",", columns), tableName,
                String.join(",", orders));
        System.out.println(result);
    }

}
