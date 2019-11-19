package com.spring.boot.mybatisplus.service.impl;

import com.spring.boot.mybatisplus.dao.entity.TenantUser;
import com.spring.boot.mybatisplus.dao.mapper.TenantUserMapper;
import com.spring.boot.mybatisplus.service.ITenantUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rock
 * @since 2019-11-19
 */
@Service
public class TenantUserServiceImpl extends ServiceImpl<TenantUserMapper, TenantUser> implements ITenantUserService {

}
