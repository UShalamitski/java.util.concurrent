package com.concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolExample {

    public static void main(String[] args) {
        System.out.println("Result = " + new ForkJoinPool(8).invoke(new MyRecursiveTask(128)));
    }

    static class MyRecursiveTask extends RecursiveTask<Long> {
        private long workLoad;

        MyRecursiveTask(long workLoad) {
            this.workLoad = workLoad;
        }

        protected Long compute() {
            //if work is above threshold, break tasks up into smaller tasks
            if(this.workLoad > 16) {
                System.out.println("Splitting work : " + this.workLoad);
                List<MyRecursiveTask> subtasks = new ArrayList<>(createSubtasks());
                subtasks.forEach(ForkJoinTask::fork);
                return subtasks.stream()
                    .map(ForkJoinTask::join)
                    .reduce(0L, (a, b) -> a + b);
            } else {
                System.out.println("Doing work myself: " + this.workLoad);
                return workLoad * 10;
            }
        }

        private List<MyRecursiveTask> createSubtasks() {
            List<MyRecursiveTask> subtasks = new ArrayList<>();
            subtasks.add(new MyRecursiveTask(this.workLoad / 2));
            subtasks.add(new MyRecursiveTask(this.workLoad / 2));
            return subtasks;
        }
    }
}
