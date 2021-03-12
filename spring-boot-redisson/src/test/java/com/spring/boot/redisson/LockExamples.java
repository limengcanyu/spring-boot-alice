package com.spring.boot.redisson;

import io.netty.channel.Channel;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.redisson.client.RedisClientConfig;
import org.redisson.client.RedisConnection;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class LockExamples {

    public static void main(String[] args) throws InterruptedException {
        // 1. Create config object
        Config config = new Config();

        // 单机配置
        config.useSingleServer().setAddress("redis://192.168.203.132:6379");
        // use "rediss://" for SSL connection
//        config.useSingleServer().setAddress("rediss://192.168.203.132:6379");

        // 集群配置
        // use "rediss://" for SSL connection
//        config.useClusterServers().addNodeAddress("redis://127.0.0.1:7181");

        RedissonClient redisson = Redisson.create(config);

        // main线程获取锁对象，请求该锁，成功后持续2秒
        System.out.println("main线程获取锁对象");
        RLock lock = redisson.getLock("lock");
        System.out.println("main线程请求锁");
        lock.lock(2, TimeUnit.SECONDS);

        Thread t = new Thread(() -> {
            System.out.println("t线程获取锁对象");
            RLock lock1 = redisson.getLock("lock");
            System.out.println("t线程请求锁");
            lock1.lock();
            System.out.println("t线程释放锁");
            lock1.unlock();
        });

        System.out.println("t线程启动");
        t.start();
        System.out.println("等待t线程结束");
        t.join();

        System.out.println("关闭redisson");
        redisson.shutdown();
    }

    /**
     * 只有获取锁的线程执行，没有获取锁的线程不执行
     *
     * @throws InterruptedException
     */
    @Test
    void lock() throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.203.132:6379");
        RedissonClient redisson = Redisson.create(config);

        Thread t1 = new Thread(() -> {
            RLock lock = redisson.getLock("myLock");
            // acquire lock and automatically unlock it after 10 seconds
            lock.lock(10, TimeUnit.SECONDS);
        });

        Thread t2 = new Thread(() -> {
            RLock lock = redisson.getLock("myLock");
            // acquire lock and automatically unlock it after 3 seconds
            lock.lock(3, TimeUnit.SECONDS);
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start));
    }

    /**
     * 公平锁：请求锁的线程都会执行，执行顺序按请求顺序
     *
     * @throws InterruptedException
     */
    @Test
    void fairLock() throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.203.132:6379");
        RedissonClient redisson = Redisson.create(config);

        Thread t1 = new Thread(() -> {
            RLock lock = redisson.getFairLock("myLock");
            try {
                // wait for lock aquisition up to 30 seconds
                // and automatically unlock it after 10 seconds
                boolean res = lock.tryLock(30, 10, TimeUnit.SECONDS);
                if (res) {
                    System.out.println("线程t1成功获取锁，10秒后释放");
                    TimeUnit.SECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程t1已经释放锁");
            }
        });

        Thread t2 = new Thread(() -> {
            RLock lock = redisson.getFairLock("myLock");
            try {
                // wait for lock aquisition up to 30 seconds
                // and automatically unlock it after 5 seconds
                boolean res = lock.tryLock(30, 5, TimeUnit.SECONDS);
                if (res) {
                    System.out.println("线程t2成功获取锁，5秒后释放");
                    TimeUnit.SECONDS.sleep(5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程t2已经释放锁");
            }
        });

        Thread t3 = new Thread(() -> {
            RLock lock = redisson.getFairLock("myLock");
            try {
                // wait for lock aquisition up to 30 seconds
                // and automatically unlock it after 3 seconds
                boolean res = lock.tryLock(30, 3, TimeUnit.SECONDS);
                if (res) {
                    System.out.println("线程t3成功获取锁，5秒后释放");
                    TimeUnit.SECONDS.sleep(3);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程t3已经释放锁");
            }
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start));
    }

    /**
     * 连锁（多个锁）：将锁对象分组，把一组锁对象当作单个锁对象处理，只有一个获取锁的线程会执行
     *
     * @throws InterruptedException
     */
    @Test
    void multiLock() throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.203.132:6379");
        RedissonClient redisson = Redisson.create(config);

        Thread t1 = new Thread(() -> {
            RLock lock1 = redisson.getLock("lock1");
            RLock lock2 = redisson.getLock("lock2");
            RLock lock3 = redisson.getLock("lock3");

            RLock multiLock = redisson.getMultiLock(lock1, lock2, lock3);

            // acquire lock and automatically unlock it after 10 seconds
            multiLock.lock(10, TimeUnit.SECONDS);
        });

        Thread t2 = new Thread(() -> {
            RLock lock1 = redisson.getLock("lock1");
            RLock lock2 = redisson.getLock("lock2");
            RLock lock3 = redisson.getLock("lock3");

            RLock multiLock = redisson.getMultiLock(lock1, lock2, lock3);

            // acquire lock and automatically unlock it after 5 seconds
            multiLock.lock(5, TimeUnit.SECONDS);
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start));
    }

    /**
     * 读写锁：只允许多读一写。
     * 注：业务方法执行时长不能超出锁持有时长，否则会抛异常，
     *
     * @throws InterruptedException
     */
    @Test
    void readWriteLock() throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.203.132:6379");
        RedissonClient redisson = Redisson.create(config);

        Thread t1 = new Thread(() -> {
            RReadWriteLock rwlock = redisson.getReadWriteLock("myLock");
            RLock lock = rwlock.readLock();
            try {
                // or wait for lock aquisition up to 30 seconds
                // and automatically unlock it after 10 seconds
                boolean res = lock.tryLock(30, 10, TimeUnit.SECONDS);
                if (res) {
                    System.out.println("读锁1成功获取锁，10秒后释放！");
                    TimeUnit.SECONDS.sleep(8);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("读锁1成功释放！");
            }
        });

        Thread t2 = new Thread(() -> {
            RReadWriteLock rwlock = redisson.getReadWriteLock("myLock");
            RLock lock = rwlock.readLock();
            try {
                // or wait for lock aquisition up to 30 seconds
                // and automatically unlock it after 5 seconds
                boolean res = lock.tryLock(30, 5, TimeUnit.SECONDS);
                if (res) {
                    System.out.println("读锁2成功获取锁，5秒后释放！");
                    TimeUnit.SECONDS.sleep(3);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("读锁2成功释放！");
            }
        });

        Thread t3 = new Thread(() -> {
            RReadWriteLock rwlock = redisson.getReadWriteLock("myLock");
            RLock lock = rwlock.writeLock();
            try {
                // or wait for lock aquisition up to 30 seconds
                // and automatically unlock it after 6 seconds
                boolean res = lock.tryLock(30, 6, TimeUnit.SECONDS);
                if (res) {
                    System.out.println("写锁1成功获取锁，6秒后释放！");
                    TimeUnit.SECONDS.sleep(4);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("写锁1成功释放！");
            }
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start));
    }

    /**
     * 信号：维护一组许可，线程获取许可
     * 注：执行后Redis会残留，需要在使用前清除已存在数据
     *
     * @throws InterruptedException
     */
    @Test
    void semaphore() throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.203.132:6379");
        RedissonClient redisson = Redisson.create(config);

        RSemaphore semaphore = redisson.getSemaphore("mySemaphore");
        System.out.println("111RSemaphore: " + semaphore + " 可用许可: " + semaphore.availablePermits());
        if (!semaphore.trySetPermits(10)) {
            System.out.println("设置许可失败！");
            return;
        }
        System.out.println("可用许可: " + semaphore.availablePermits());

        Thread t1 = new Thread(() -> {
            System.out.println("线程t1开始执行=======================================================");
            System.out.println("222RSemaphore: " + semaphore + " 可用许可: " + semaphore.availablePermits());

            try {
                // try to acquire 10 permits or wait up to 15 seconds
                boolean res = semaphore.tryAcquire(10, 15, TimeUnit.SECONDS);
                if (res) {
                    try {
                        System.out.println("线程t1成功获取10个许可，10秒后释放");
                        TimeUnit.SECONDS.sleep(10);
                    } finally {
                        semaphore.release(10);
                        System.out.println("线程t1成功释放10个许可！" + " 可用许可: " + semaphore.availablePermits());
                    }
                } else {
                    System.out.println("线程t1获取许可失败！");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程t1结束执行=======================================================");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("线程t2开始执行=======================================================");
            System.out.println("333RSemaphore: " + semaphore + " 可用许可: " + semaphore.availablePermits());

            try {
                // try to acquire 10 permits or wait up to 10 seconds
                boolean res = semaphore.tryAcquire(10, 10, TimeUnit.SECONDS);
                if (res) {
                    try {
                        System.out.println("线程t2成功获取10个许可，5秒后释放");
                        TimeUnit.SECONDS.sleep(5);
                    } finally {
                        semaphore.release(10);
                        System.out.println("线程t2成功释放10个许可！" + " 可用许可: " + semaphore.availablePermits());
                    }
                } else {
                    System.out.println("线程t2获取许可失败！");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程t2结束执行=======================================================");
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start));
    }
}
