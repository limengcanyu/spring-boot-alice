package com.spring.boot.annotation.service.impl;

import com.spring.boot.annotation.annotation.OperationalAudit;
import com.spring.boot.annotation.constant.OperationalTypeConst;
import com.spring.boot.annotation.service.MysqlService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MysqlServiceImpl implements MysqlService {

    @OperationalAudit(name = "addRecord", type = OperationalTypeConst.OPERATIONAL_TYPE_KEY_ADD)
    @Override
    public String addRecord() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
