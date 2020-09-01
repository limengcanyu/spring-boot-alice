package com.spring.boot.shardingsphere.jdbc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.boot.shardingsphere.jdbc.dao.entity.TOrder;
import com.spring.boot.shardingsphere.jdbc.service.ITOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;

@SpringBootTest
class SpringBootShardingsphereJdbcApplicationTests {

    @Autowired
    private ITOrderService orderService;

    @Test
    void save() {
        long start = System.currentTimeMillis();

        for (int i = 1; i <= 100; i++) {
            TOrder tOrder = new TOrder();
            tOrder.setOrderId(i);
            tOrder.setUserId(i);
            orderService.save(tOrder);
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start));
    }

    @Test
    void getById() {
        TOrder tOrder = orderService.getById(2);
        System.out.println(tOrder);
    }

    @Test
    void list() {
        LambdaQueryWrapper<TOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(TOrder::getUserId, "user");
        List<TOrder> tOrderList = orderService.list(queryWrapper);
        tOrderList.sort(Comparator.comparing(TOrder::getUserId));
        for (TOrder tOrder : tOrderList) {
            System.out.println(tOrder);
        }
    }

}
