package com.spring.boot.async.service;

public interface AsyncTaskWithoutResultService {
    void task1() throws InterruptedException;
    void task2() throws InterruptedException;
    void task3() throws InterruptedException;
}
