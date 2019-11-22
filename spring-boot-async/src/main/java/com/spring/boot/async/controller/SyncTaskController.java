package com.spring.boot.async.controller;


import com.spring.boot.async.service.SyncTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class SyncTaskController {
    private static final Logger logger = LoggerFactory.getLogger(SyncTaskController.class);

    @Autowired
    private SyncTaskService syncTaskService;

    /**
     * 执行无返回任务
     *
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/doSyncTask")
    public String doSyncTask() throws InterruptedException {
        logger.debug("controller 任务 开始 线程 id: {} name: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
        long currentTimeMillis = System.currentTimeMillis();
        syncTaskService.task1();
        logger.debug("controller 任务 结束 线程 id: {} name: {} 耗时: {}", Thread.currentThread().getId(), Thread.currentThread().getName(), (System.currentTimeMillis() - currentTimeMillis) + "ms");
        return "操作开始，请等候完成！";
    }
}
