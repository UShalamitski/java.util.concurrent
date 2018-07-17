package com.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        new Thread(() -> {
            try {
                queue.put("1");
                Thread.sleep(1000);
                queue.put("2");
                Thread.sleep(1000);
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println(queue.take());
                System.out.println(queue.take());
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
