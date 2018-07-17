package com.concurrent.synchronizers;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class SemaphoreExample {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        IntStream.range(0, 4).forEach(index -> {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " acquire lock");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " release lock");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }
}
