package com.spring.boot.async.controller;


import com.spring.boot.async.service.AsyncTaskWithResultService;
import com.spring.boot.async.service.AsyncTaskWithoutResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@RestController
@RequestMapping("")
public class AsyncTaskController {
    private static final Logger logger = LoggerFactory.getLogger(AsyncTaskController.class);

    @Autowired
    private AsyncTaskWithoutResultService asyncTaskWithoutResultService;

    @Autowired
    private AsyncTaskWithResultService asyncTaskWithResultService;

    /**
     * 执行无返回任务
     *
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/doWithoutReturnTask")
    public String doWithoutReturnTask() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        asyncTaskWithoutResultService.task1();
        asyncTaskWithoutResultService.task2();
        asyncTaskWithoutResultService.task3();
        long currentTimeMillis1 = System.currentTimeMillis();
        return "task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms";
    }

    /**
     * 执行返回Future任务
     *
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/doReturnFutureResultTask")
    public String doReturnFutureResultTask() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Future<String> task1 = asyncTaskWithResultService.task1();
        Future<String> task2 = asyncTaskWithResultService.task2();
        Future<String> task3 = asyncTaskWithResultService.task3();
        String result = null;

        // 三个任务都调用完成，退出循环等待
        // 异步调用成功，并且在所有任务都完成时程序才返回了结果！
        while (!task1.isDone() || !task2.isDone() || !task3.isDone()) {
            Thread.sleep(1000);
        }

        long currentTimeMillis1 = System.currentTimeMillis();
        result = "task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms";
        return result;
    }

    /**
     * 执行返回ListenableFuture任务
     * 可以对结果进行更多操作
     *
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/doReturnListenableFutureResultTask")
    public String doReturnListenableFutureResultTask() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        ListenableFuture<String> task4 = asyncTaskWithResultService.task4();
        // 为异步结果添加成功回调方法，失败回调方法
        task4.addCallback(successResult -> {
            logger.debug("任务4执行 成功！");
        }, failResult -> {
            logger.debug("任务4执行 失败！");
        });
        ListenableFuture<String> task5 = asyncTaskWithResultService.task5();
        ListenableFuture<String> task6 = asyncTaskWithResultService.task6();
        String result = null;

        // 三个任务都调用完成，退出循环等待
        // 异步调用成功，并且在所有任务都完成时程序才返回了结果！
        while (!task4.isDone() || !task5.isDone() || !task6.isDone()) {
            Thread.sleep(1000);
        }

        long currentTimeMillis1 = System.currentTimeMillis();
        result = "task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms";
        return result;
    }

    /**
     * 执行返回CompletableFuture任务
     * 可以对结果进行更多操作
     *
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/doReturnCompletableFutureResultTask")
    public String doReturnCompletableFutureResultTask() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        CompletableFuture<String> task7 = asyncTaskWithResultService.task7();
        CompletableFuture<String> task8 = asyncTaskWithResultService.task8();
        CompletableFuture<String> task9 = asyncTaskWithResultService.task9();
        String result = null;

        // 三个任务都调用完成，退出循环等待
        // 异步调用成功，并且在所有任务都完成时程序才返回了结果！
        while (!task7.isDone() || !task8.isDone() || !task9.isDone()) {
            Thread.sleep(1000);
        }

        long currentTimeMillis1 = System.currentTimeMillis();
        result = "task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms";
        return result;
    }
}
