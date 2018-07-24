package com.spring.boot.mybatisplus.druid.multidatasource.artanis.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spring.boot.mybatisplus.druid.multidatasource.artanis.dao.entity.SysUser;
import com.spring.boot.mybatisplus.druid.multidatasource.artanis.dao.mapper.SysUserMapper;
import com.spring.boot.mybatisplus.druid.multidatasource.artanis.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Rock.Jiang
 * @since 2018-05-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
