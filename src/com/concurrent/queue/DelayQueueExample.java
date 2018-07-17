package com.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {

    public static void main(String[] args) {
        BlockingQueue<DelayObject> queue = new DelayQueue<>();

        new Thread(() -> {
            try {
                queue.put(new DelayObject("First", 5000));
                queue.put(new DelayObject("Second", 1000));
                queue.put(new DelayObject("Third", 2000));
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

    static class DelayObject implements Delayed {
        private String data;
        private long delay;
        private long startTime;

        DelayObject(String data, long delay) {
            this.data = data;
            this.delay = delay;
            this.startTime = System.currentTimeMillis() + delay;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(startTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed obj) {
            return (int) (this.startTime - ((DelayObject) obj).startTime);
        }

        @Override
        public String toString() {
            return String.format("Element [%s] with delay [%s]", data, delay);
        }
    }
}
