//package com.spring.boot.mybatisplus.druid.multidatasource.service;
//
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.spring.boot.mybatisplus.druid.multidatasource.DruidMultiDatasourceApplication;
//import com.spring.boot.mybatisplus.druid.multidatasource.artanis.dao.entity.SysUser;
//import com.spring.boot.mybatisplus.druid.multidatasource.artanis.service.SysUserService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
///**
// * <p>Description: </p>
// *
// * @author Rock Jiang
// * @version 1.0
// * @date 2018/5/23 0023
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DruidMultiDatasourceApplication.class)
//public class SysUserServiceTest {
//    @Autowired
//    SysUserService sysUserService;
//
//    @Test
//    public void select() {
//        EntityWrapper<SysUser> entityWrapper = new EntityWrapper<>();
//        List<SysUser> sysUserList = sysUserService.selectList(entityWrapper);
//        System.out.println("-------------");
//    }
//}
