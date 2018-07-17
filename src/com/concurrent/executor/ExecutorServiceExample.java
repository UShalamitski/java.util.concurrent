package com.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ExecutorServiceExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10).forEach(index -> {
            executor.submit(() -> {
                try {
                    System.out.println(
                            String.format("[%s] is executing task [%s]", Thread.currentThread().getName(), index));
                    Thread.sleep(index * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        executor.shutdown();
    }
}
