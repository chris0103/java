package org.chris.study.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo2 {

    public static void main(String[] args) throws InterruptedException {
        int count = 5;
        CountDownLatch doneSignal = new CountDownLatch(count);
        ExecutorService e = Executors.newFixedThreadPool(count);
        System.out.println("Threads ready to go!");
        for (int i = 1; i <= 5; i++) {
            e.execute(new Thread(new WorkerRunnable(doneSignal, "round " + i)));
        }
        System.out.println("Waiting for threads to finish...");
        doneSignal.await();
        e.shutdown();
        System.out.println("All threads finished.");
    }
    
    static class WorkerRunnable implements Runnable {
        
        private final CountDownLatch doneSignal;
        private final String text;

        public WorkerRunnable(CountDownLatch doneSignal, String text) {
            this.doneSignal = doneSignal;
            this.text = text;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " started.");
            System.out.println(Thread.currentThread().getName() + ": " + text);
            System.out.println(Thread.currentThread().getName() + " finished.");
            doneSignal.countDown();
        }
        
    }

}
