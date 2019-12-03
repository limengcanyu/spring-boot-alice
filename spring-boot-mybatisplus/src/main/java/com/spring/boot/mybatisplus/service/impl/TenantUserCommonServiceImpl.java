package com.spring.boot.mybatisplus.service.impl;

import com.spring.boot.mybatisplus.dao.entity.TenantUser;
import com.spring.boot.mybatisplus.service.ITenantUserService;
import com.spring.boot.mybatisplus.service.TenantUserCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2019/12/03 19:49
 */
@Service
public class TenantUserCommonServiceImpl implements TenantUserCommonService {
    @Autowired
    private ITenantUserService tenantUserService;

    /**
     * 调用自身类
     *
     * 调用者无事务，被调用者有事务
     */
    @Override
    public void method1(String name) throws Exception {
        method3(name);
    }

    /**
     * 调用自身类
     *
     * 调用者有事务，被调用者有事务
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void method2(String name) throws Exception {
        method3(name);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void method3(String name) throws Exception {
        TenantUser updateUser = new TenantUser();
        updateUser.setId(1L);
        updateUser.setName(name);
        tenantUserService.updateById(updateUser);

        throw new Exception();
    }
}
