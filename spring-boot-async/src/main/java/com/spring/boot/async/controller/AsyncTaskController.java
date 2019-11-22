package com.spring.boot.async.controller;


import com.spring.boot.async.service.AsyncTaskWithResultService;
import com.spring.boot.async.service.AsyncTaskWithoutResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

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
        logger.debug("controller 任务 开始 线程 id: {} name: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
        long currentTimeMillis = System.currentTimeMillis();

        asyncTaskWithoutResultService.task1();
        asyncTaskWithoutResultService.task2();
        asyncTaskWithoutResultService.task3();

        String result = "执行任务耗时: " + (System.currentTimeMillis() - currentTimeMillis) + "ms";
        logger.debug("controller 任务 结束 线程 id: {} name: {} 耗时: {}", Thread.currentThread().getId(), Thread.currentThread().getName(), result);
        return result;
    }

    @RequestMapping("/doWithoutReturnTask2")
    public String doWithoutReturnTask2() throws InterruptedException {
        logger.debug("controller 任务 开始 线程 id: {} name: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
        long currentTimeMillis = System.currentTimeMillis();

        asyncTaskWithoutResultService.task1();

        String result = "执行任务耗时: " + (System.currentTimeMillis() - currentTimeMillis) + "ms";
        logger.debug("controller 任务 结束 线程 id: {} name: {} 耗时: {}", Thread.currentThread().getId(), Thread.currentThread().getName(), result);
        return result;
    }

    /**
     * 执行返回Future任务
     *
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/doReturnFutureResultTask")
    public String doReturnFutureResultTask() throws InterruptedException, TimeoutException, ExecutionException {
        logger.debug("controller 任务 开始 线程 id: {} name: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
        long currentTimeMillis = System.currentTimeMillis();
        Future<String> task1 = asyncTaskWithResultService.task1();
        Future<String> task2 = asyncTaskWithResultService.task2();
        Future<String> task3 = asyncTaskWithResultService.task3();

        // 三个任务都调用完成，退出循环等待
        // 异步调用成功，并且在所有任务都完成时程序才返回了结果！
        while (!task1.isDone() || !task2.isDone() || !task3.isDone()) {
            Thread.sleep(1000);
        }

        // 等待获取异步任务结果，3分钟超时
        logger.debug("controller 异步任务结果 task1: {}", task1.get(3, TimeUnit.MINUTES));
        logger.debug("controller 异步任务结果 task2: {}", task2.get(3, TimeUnit.MINUTES));
        logger.debug("controller 异步任务结果 task3: {}", task3.get(3, TimeUnit.MINUTES));

        String result = "执行任务耗时: " + (System.currentTimeMillis() - currentTimeMillis) + "ms";
        logger.debug("controller 任务 结束 线程 id: {} name: {} 耗时: {}", Thread.currentThread().getId(), Thread.currentThread().getName(), result);
        return result;
    }

    @RequestMapping("/doReturnFutureResultTask2")
    public String doReturnFutureResultTask2() throws InterruptedException, TimeoutException, ExecutionException {
        logger.debug("controller 任务 开始 线程 id: {} name: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
        long currentTimeMillis = System.currentTimeMillis();

        Future<String> task1 = asyncTaskWithResultService.task1();
        logger.debug("controller 异步任务结果 task1: {}", task1.get(3, TimeUnit.MINUTES));

        String result = "执行任务耗时: " + (System.currentTimeMillis() - currentTimeMillis) + "ms";
        logger.debug("controller 任务 结束 线程 id: {} name: {} 耗时: {}", Thread.currentThread().getId(), Thread.currentThread().getName(), result);
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
        logger.debug("controller 任务 开始 线程 id: {} name: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
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

        // 三个任务都调用完成，退出循环等待
        // 异步调用成功，并且在所有任务都完成时程序才返回了结果！
        while (!task4.isDone() || !task5.isDone() || !task6.isDone()) {
            Thread.sleep(1000);
        }

        String result = "执行任务耗时: " + (System.currentTimeMillis() - currentTimeMillis) + "ms";
        logger.debug("controller 任务 结束 线程 id: {} name: {} 耗时: {}", Thread.currentThread().getId(), Thread.currentThread().getName(), result);
        return result;
    }

    @RequestMapping("/doReturnListenableFutureResultTask2")
    public String doReturnListenableFutureResultTask2() throws InterruptedException, ExecutionException {
        logger.debug("controller 任务 开始 线程 id: {} name: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
        long currentTimeMillis = System.currentTimeMillis();

        ListenableFuture<String> task4 = asyncTaskWithResultService.task4();

        final String[] taskResult = {null};

        // 为异步结果添加成功回调方法，失败回调方法
//        task4.addCallback(result -> {
//            // 异步任务成功，对结果做些操作
//            logger.debug("controller 异步任务执行 成功！");
//            taskResult[0] = result;
//        }, ex -> {
//            // 异步任务失败，打印异常
//            logger.debug("controller 异步任务执行 失败！");
//            ex.printStackTrace();
//        });
        task4.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onFailure(Throwable ex) {
                // 异步任务失败，打印异常
                logger.debug("controller 异步任务执行 失败！");
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(String result) {
                // 异步任务成功，对结果做些操作
                logger.debug("controller 异步任务执行 成功！");
                taskResult[0] = result;
            }
        });
        logger.debug("controller 异步任务结果 task4: {}", taskResult[0]);

//        // 等待异步调用完成
//        CompletableFuture.allOf(task4.completable()).join();
//        logger.debug("controller 异步任务结果 task4: {}", task4.get());

        String result = "执行任务耗时: " + (System.currentTimeMillis() - currentTimeMillis) + "ms";
        logger.debug("controller 任务 结束 线程 id: {} name: {} 耗时: {}", Thread.currentThread().getId(), Thread.currentThread().getName(), result);
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
    public String doReturnCompletableFutureResultTask() throws InterruptedException, ExecutionException {
        logger.debug("controller 任务 开始 线程 id: {} name: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
        long currentTimeMillis = System.currentTimeMillis();
        CompletableFuture<String> task7 = asyncTaskWithResultService.task7();
        CompletableFuture<String> task8 = asyncTaskWithResultService.task8();
        CompletableFuture<String> task9 = asyncTaskWithResultService.task9();

        // 等待每个异步调用都完成
        CompletableFuture.allOf(task7, task8, task9).join();
        logger.debug("controller 异步任务结果 task7: {} task8: {} task9: {}", task7.get(), task8.get(), task9.get());

        String result = "执行任务耗时: " + (System.currentTimeMillis() - currentTimeMillis) + "ms";
        logger.debug("controller 任务 结束 线程 id: {} name: {} 耗时: {}", Thread.currentThread().getId(), Thread.currentThread().getName(), result);
        return result;
    }

    @RequestMapping("/doReturnCompletableFutureResultTask2")
    public String doReturnCompletableFutureResultTask2() throws InterruptedException, ExecutionException {
        logger.debug("controller 任务 开始 线程 id: {} name: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
        long currentTimeMillis = System.currentTimeMillis();

        CompletableFuture<String> task7 = asyncTaskWithResultService.task7();

        // 等待异步调用完成
        CompletableFuture.allOf(task7).join();
        logger.debug("controller 异步任务结果 task7: {}", task7.get());

        String result = "执行任务耗时: " + (System.currentTimeMillis() - currentTimeMillis) + "ms";
        logger.debug("controller 任务 结束 线程 id: {} name: {} 耗时: {}", Thread.currentThread().getId(), Thread.currentThread().getName(), result);
        return result;
    }
}
