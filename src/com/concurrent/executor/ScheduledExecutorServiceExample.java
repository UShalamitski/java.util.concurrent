package com.concurrent.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ScheduledExecutorServiceExample {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        IntStream.range(0, 10).forEach(index -> {
            executor.schedule(() -> {
                System.out.println(
                        String.format("[%s] is executing task [%s]", Thread.currentThread().getName(), index));
            }, 5, TimeUnit.SECONDS);
        });
        executor.shutdown();
    }
}
