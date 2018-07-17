package com.concurrent.synchronizers;

import java.util.concurrent.Exchanger;

public class ExchangerExample {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new ExchangerRunnable(exchanger, "Awl")).start();
        new Thread(new ExchangerRunnable(exchanger, "Soup")).start();
    }

    static class ExchangerRunnable implements Runnable {
        Exchanger<String> exchanger;
        String value;

        ExchangerRunnable(Exchanger<String> exchanger, String value) {
            this.exchanger = exchanger;
            this.value = value;
        }

        public void run() {
            try {
                String newValue = exchanger.exchange(value);
                System.out.println(String.format(
                        "[%s] exchanged [%s] for [%s]", Thread.currentThread().getName(), value, newValue));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
