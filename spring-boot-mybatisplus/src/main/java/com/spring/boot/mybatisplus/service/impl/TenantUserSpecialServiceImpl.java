package com.spring.boot.mybatisplus.service.impl;

import com.spring.boot.mybatisplus.service.TenantUserCommonService;
import com.spring.boot.mybatisplus.service.TenantUserSpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2019/12/03 20:23
 */
@Service
public class TenantUserSpecialServiceImpl implements TenantUserSpecialService {
    @Autowired
    private TenantUserCommonService tenantUserCommonService;

    /**
     * 调用其它类
     *
     * 调用者无事务，被调用者有事务
     *
     * Service 调用 Service
     *
     * @param name
     * @throws Exception
     */
    @Override
    public void method1(String name) throws Exception {
        tenantUserCommonService.method3(name);
    }

    /**
     * 调用其它类
     *
     * 调用者有事务，被调用者有事务
     *
     * Service 调用 Service
     *
     * @param name
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void method2(String name) throws Exception {
        tenantUserCommonService.method3(name);
    }
}
