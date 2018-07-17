package com.concurrent.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

public class ReadWriteLockExample {

    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        IntStream.range(1, 5).forEach(index ->
            new Thread(() -> {
                try {
                    lock.readLock().lock();
                    System.out.println(Thread.currentThread().getName() + " acquired the read lock");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " released the read lock");
                    lock.readLock().unlock();

                    lock.writeLock().lock();
                    System.out.println(Thread.currentThread().getName() + " acquired the write lock");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " released the write lock");
                    lock.writeLock().unlock();
                } catch (InterruptedException | IllegalMonitorStateException e) {
                    e.printStackTrace();
                }
            }).start()
        );
    }
}
