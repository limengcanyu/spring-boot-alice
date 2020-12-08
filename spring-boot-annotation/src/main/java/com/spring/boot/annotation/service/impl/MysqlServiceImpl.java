package com.spring.boot.annotation.service.impl;

import com.spring.boot.annotation.annotation.MultiTransactional;
import com.spring.boot.annotation.service.MysqlService;
import org.springframework.stereotype.Service;

@Service
public class MysqlServiceImpl implements MysqlService {

    @MultiTransactional(transactionManager = {"mysqlTransactionManager", "mongoTransactionManager"})
    @Override
    public String addRecord() {
        return "success";
    }
}
