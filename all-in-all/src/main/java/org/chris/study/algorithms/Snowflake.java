package org.chris.study.algorithms;

public class Snowflake {

    // epoch offset to max utilize timestamp bits
    private final long twepoch = 1288834974657L;

    // bits for workerId, datacenterId, and sequence
    private final long workerIdBits = 5L;
    private final long datacenterIdBits = 5L;
    private final long sequenceBits = 12L;

    // max values for workerId, datacenterId
    private final long maxWorkerId = ~(-1L << workerIdBits);
    private final long maxDatacenterId = ~(-1L << datacenterIdBits);

    // max value, and also mask for sequence
    private final long sequenceMask = ~(-1L << sequenceBits);

    // shifts for workerId, datacenterId, and timestamp
    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    private long lastTimestamp = -1L;

    private long workerId;
    private long datacenterId;
    private long sequence;

    public Snowflake(long workerId, long datacenterId, long sequence) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("Worker ID can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("Datacenter ID can't be greater than %d or less than 0", maxDatacenterId));
        }
        System.out.printf("Worker starting... datacenter ID: %d, Worker ID: %d%n", datacenterId, workerId);

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards. Rejecting requests until %d", lastTimestamp);
            throw new RuntimeException(
                    String.format("Clock moving backwards. Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        Snowflake idGenerator = new Snowflake(1, 1,1);
        for (int i = 0; i < 10; i++) {
            long id = idGenerator.nextId();
            System.out.println(String.format("%64s", Long.toBinaryString(id)).replace(' ', '0')
                + " (" + id + ")");
        }
    }
}
