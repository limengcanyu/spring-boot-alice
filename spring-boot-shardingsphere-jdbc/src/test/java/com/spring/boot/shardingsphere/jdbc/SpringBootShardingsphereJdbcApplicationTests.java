package com.spring.boot.shardingsphere.jdbc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.boot.shardingsphere.jdbc.dao.entity.TOrder;
import com.spring.boot.shardingsphere.jdbc.dao.entity.TOrderItem;
import com.spring.boot.shardingsphere.jdbc.service.ITOrderItemService;
import com.spring.boot.shardingsphere.jdbc.service.ITOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
class SpringBootShardingsphereJdbcApplicationTests {
    @Resource
    private DataSource dataSource;

    @Autowired
    private ITOrderService orderService;

    @Autowired
    private ITOrderItemService orderItemService;

    @Test
    void getDataSource() {
        System.out.println(dataSource.getClass());
    }

    @Test
    void save() {
        long start = System.currentTimeMillis();

        for (int i = 1; i <= 100; i++) {
            TOrder tOrder = new TOrder();
            tOrder.setUserId(i);
            tOrder.setOrderId(i);
            tOrder.setCreateTime(LocalDateTime.now());
            orderService.save(tOrder);

            TOrderItem tOrderItem = new TOrderItem();
            tOrderItem.setUserId(i);
            tOrderItem.setOrderId(i);
            tOrderItem.setOrderItemId(i);
            tOrderItem.setCreateTime(LocalDateTime.now());
            orderItemService.save(tOrderItem);
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
        queryWrapper.lt(TOrder::getUserId, 20000);
        List<TOrder> tOrderList = orderService.list(queryWrapper);
        tOrderList.sort(Comparator.comparing(TOrder::getUserId));
        for (TOrder tOrder : tOrderList) {
            System.out.println(tOrder);
        }
    }

}
