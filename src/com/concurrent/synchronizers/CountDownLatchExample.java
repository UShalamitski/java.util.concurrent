package com.concurrent.synchronizers;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CountDownLatchExample {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Waiter Released");
        }).start();

        IntStream.range(1, 4).forEach(index -> {
            new Thread(() -> {
                try {
                    Thread.sleep(index * 1000);
                    System.out.println(String.format("Task [%s] finished", index));
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }
}
