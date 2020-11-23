package org.chris.study.concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo1 {

    public static void main(String[] args) throws InterruptedException {
        int count = 5;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(count);
        
        for (int i = 0; i < count; i++) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }
        for (int i = 3; i >0; i--) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        System.out.println("Threads ready to go!");
        startSignal.countDown();
        System.out.println("Waiting for threads to finish...");
        doneSignal.await();
        System.out.println("All threads finished.");
    }
    
    static class Worker implements Runnable {

        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        
        public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            try {
                startSignal.await();
                System.out.println(Thread.currentThread().getName() + " started.");
                System.out.println(Thread.currentThread().getName() + " finished.");
                doneSignal.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
        
    }

}
