package com.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

        new Thread(() -> {
            try {
                queue.put(7);
                Thread.sleep(1000);
                queue.put(2);
                Thread.sleep(1000);
                queue.put(4);
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
