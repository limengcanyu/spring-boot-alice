package com.spring.boot.mybatisplus.controller;


import com.spring.boot.mybatisplus.service.TenantUserCommonService;
import com.spring.boot.mybatisplus.service.TenantUserSpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rock
 * @since 2019-11-19
 */
@RestController
@RequestMapping("/tenantUser")
public class TenantUserController {
    @Autowired
    private TenantUserCommonService tenantUserCommonService;

    @Autowired
    private TenantUserSpecialService tenantUserSpecialService;

    /**
     * 调用自身类
     *
     * 调用者无事务，被调用者有事务
     *
     * 会更新记录，事务无效
     *
     * localhost:8080/tenantUser/method1
     */
    @RequestMapping("/method1")
    public void method1() {
        try {
            tenantUserCommonService.method1("Artanis");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用自身类
     *
     * 调用者有事务，被调用者有事务
     *
     * 不会更新记录，事务有效
     *
     * localhost:8080/tenantUser/method2
     */
    @RequestMapping("/method2")
    public void method2() {
        try {
            tenantUserCommonService.method2("Samuro");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用其它类
     *
     * 调用者无事务，被调用者有事务
     *
     * Controller 中无事务，Service 中有事务
     *
     * 不会更新记录，事务有效
     *
     * localhost:8080/tenantUser/method3
     */
    @RequestMapping("/method3")
    public void method3() {
        try {
            tenantUserCommonService.method3("Samuro");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用其它类
     *
     * 调用者有事务，被调用者有事务
     *
     * Controller 中有事务，Service 中有事务
     *
     * 不会更新记录，事务有效，异常会抛到前端
     *
     * localhost:8080/tenantUser/method4
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/method4")
    public void method4() {
        try {
            tenantUserCommonService.method3("Samuro");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用其它类
     *
     * 调用者无事务，被调用者有事务
     *
     * Service 调用 Service
     *
     * 不会更新记录，事务有效
     *
     * localhost:8080/tenantUser/method5
     */
    @RequestMapping("/method5")
    public void method5() {
        try {
            tenantUserSpecialService.method1("Samuro");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用其它类
     *
     * 调用者有事务，被调用者有事务
     *
     * Service 调用 Service
     *
     * 不会更新记录，事务有效
     *
     * localhost:8080/tenantUser/method6
     */
    @RequestMapping("/method6")
    public void method6() {
        try {
            tenantUserSpecialService.method2("Samuro");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

