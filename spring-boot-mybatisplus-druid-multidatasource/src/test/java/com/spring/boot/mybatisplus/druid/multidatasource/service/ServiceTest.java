package com.spring.boot.mybatisplus.druid.multidatasource.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.spring.boot.mybatisplus.druid.multidatasource.DruidMultiDatasourceApplication;
import com.spring.boot.mybatisplus.druid.multidatasource.artanis.dao.entity.SysUser;
import com.spring.boot.mybatisplus.druid.multidatasource.artanis.service.SysUserService;
import com.spring.boot.mybatisplus.druid.multidatasource.samuro.dao.entity.SysCompany;
import com.spring.boot.mybatisplus.druid.multidatasource.samuro.service.SysCompanyService;
import com.spring.boot.mybatisplus.druid.multidatasource.valeera.dao.entity.SysOrder;
import com.spring.boot.mybatisplus.druid.multidatasource.valeera.service.SysOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/5/23 0023
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DruidMultiDatasourceApplication.class)
public class ServiceTest {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysCompanyService sysCompanyService;
    @Autowired
    SysOrderService sysOrderService;

    @Test
    public void select() {
        //阿塔尼斯数据库表
        EntityWrapper<SysUser> entityWrapper = new EntityWrapper<>();
        List<SysUser> sysUserList = sysUserService.selectList(entityWrapper);
        System.out.println("sysUserList: " + JSONObject.toJSONString(sysUserList));

        //萨穆罗数据库表
        EntityWrapper<SysCompany> companyEntityWrapper = new EntityWrapper<>();
        List<SysCompany> companyList = sysCompanyService.selectList(companyEntityWrapper);
        System.out.println("companyList: " + JSONObject.toJSONString(companyList));

        //瓦莉拉数据库表
        EntityWrapper<SysOrder> orderEntityWrapper = new EntityWrapper<>();
        List<SysOrder> orderList = sysOrderService.selectList(orderEntityWrapper);
        System.out.println("orderList: " + JSONObject.toJSONString(orderList));

    }
}
