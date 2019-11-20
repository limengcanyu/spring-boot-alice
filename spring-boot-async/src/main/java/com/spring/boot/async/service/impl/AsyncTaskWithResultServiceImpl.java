package com.spring.boot.async.service.impl;

import com.spring.boot.async.service.AsyncTaskWithResultService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class AsyncTaskWithResultServiceImpl implements AsyncTaskWithResultService {
    @Async
    @Override
    public Future<String> task1() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task1任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<>("task1执行完毕");
    }

    @Async
    @Override
    public Future<String> task2() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(2000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task2任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<>("task2执行完毕");
    }

    @Async
    @Override
    public Future<String> task3() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task3任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<>("task3执行完毕");
    }

    @Override
    public ListenableFuture<String> task4() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task4任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<>("task4执行完毕");
    }

    @Override
    public ListenableFuture<String> task5() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(2000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task5任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<>("task5执行完毕");
    }

    @Override
    public ListenableFuture<String> task6() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task6任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<>("task6执行完毕");
    }

    @Override
    public CompletableFuture<String> task7() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task7任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        // 使用结果返回一个已完成的CompletableFuture
        return CompletableFuture.completedFuture("task7执行完毕");
    }

    @Override
    public CompletableFuture<String> task8() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(2000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task8任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return CompletableFuture.completedFuture("task8执行完毕");
    }

    @Override
    public CompletableFuture<String> task9() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task9任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return CompletableFuture.completedFuture("task9执行完毕");
    }
}
