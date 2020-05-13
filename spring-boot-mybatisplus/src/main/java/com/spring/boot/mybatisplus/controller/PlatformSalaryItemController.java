package com.spring.boot.mybatisplus.controller;


import com.spring.boot.mybatisplus.service.IPlatformSalaryItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * <p>
 * 平台薪资项定义信息表 前端控制器
 * </p>
 *
 * @author rock.jiang
 * @since 2020-05-13
 */
@Slf4j
@RestController
@RequestMapping("/platform-salary-item")
public class PlatformSalaryItemController {

    @Autowired
    private IPlatformSalaryItemService platformSalaryItemService;

    @Autowired
    private DataSource dataSource;

    /**
     * localhost:8080/platform-salary-item/getItem
     *
     * @return
     */
    @RequestMapping("/getItem")
    public Object getItem() {
        log.debug("dataSource: {}", dataSource.getClass());
        return platformSalaryItemService.getById(26);
    }
}
