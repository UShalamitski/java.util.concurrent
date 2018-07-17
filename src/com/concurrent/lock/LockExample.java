package com.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class LockExample {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        IntStream.range(1, 5).forEach(index ->
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " tries to acquire the lock");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " acquired the lock");
                    Thread.sleep(index * 1000);
                    System.out.println(Thread.currentThread().getName() + " released the lock");
                    lock.unlock();
                } catch (InterruptedException | IllegalMonitorStateException e) {
                    e.printStackTrace();
                }
            }).start()
        );
    }
}
