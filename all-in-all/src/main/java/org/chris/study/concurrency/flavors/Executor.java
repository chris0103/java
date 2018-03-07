package org.chris.study.concurrency.flavors;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Executor implements NumberPrinter {

    private final Logger logger = LoggerFactory.getLogger(Executor.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPrinterName() {
        return "The executor printer";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int toNumber(List<Integer> nums) {
        AtomicInteger sum = new AtomicInteger();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int num : nums) {
            executor.submit(() -> {
                System.out.print(num + "\t");
                sum.addAndGet(num);
            });
        }
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            logger.error("Failed to await executor termination.", e);
            executor.shutdownNow();
        }
        return sum.get();
    }
}
