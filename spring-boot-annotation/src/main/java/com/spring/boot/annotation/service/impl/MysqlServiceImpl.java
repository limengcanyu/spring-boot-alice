package com.spring.boot.annotation.service.impl;

import com.spring.boot.annotation.service.MysqlService;
import org.springframework.stereotype.Service;

@Service
public class MysqlServiceImpl implements MysqlService {

    @Override
    public String addRecord() {
        return "success";
    }
}
