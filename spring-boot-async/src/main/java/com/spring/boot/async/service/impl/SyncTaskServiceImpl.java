package com.spring.boot.async.service.impl;

import com.spring.boot.async.service.SyncTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SyncTaskServiceImpl implements SyncTaskService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncTaskWithoutResultServiceImpl.class);

    @Override
    public void task1() throws InterruptedException {
        logger.debug("service 任务 开始 线程 id: {} name: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        logger.debug("service 任务 结束 线程 id: {} name: {} 耗时: {}", Thread.currentThread().getId(), Thread.currentThread().getName(), (System.currentTimeMillis() - currentTimeMillis) + "ms");
    }
}
