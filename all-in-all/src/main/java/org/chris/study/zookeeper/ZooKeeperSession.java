package org.chris.study.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * ZooKeeperSession
 */
public class ZooKeeperSession {

    private static final Logger logger = LoggerFactory.getLogger(ZooKeeperSession.class);

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private ZooKeeper zookeeper;
    private ThreadLocal<CountDownLatch> latch;
    private long waitTime = 1000;

    /**
     * Constructs ZooKeeperSession.
     */
    public ZooKeeperSession() {
        try {
            this.zookeeper = new ZooKeeper("localhost:2181", 50000, new ZooKeeperWatcher());

            // get the session state
            logger.info("Connection state: " + zookeeper.getState());
            try {
                connectedSemaphore.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("ZooKeeper session established...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取分布式锁
     *
     * @param productId
     */
    public Boolean acquireDistributedLock(int threadId, int productId) {
        String path = "/product-lock-" + productId;

        try {
            zookeeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            logger.info("Thread " + threadId + " acquired the lock for product [id = " + productId + "] ......");
            return true;
        } catch (Exception e) {
            logger.warn("Thread " + threadId + " failed to acquire the lock for product [id = " + productId + "] ......");
            while (true) {
                try {
                    // register a watcher for the node, to see if the node exists
                    Stat stat = zookeeper.exists(path, true);

                    if (stat != null) {
                        this.latch.set(new CountDownLatch(1));
                        this.latch.get().await(waitTime, TimeUnit.MILLISECONDS);
                        this.latch = null;
                    }
                    zookeeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                    logger.info("Thread " + threadId + " acquired the lock for product [id = " + productId + "] ......");
                    return true;
                } catch (Exception ee) {
                    continue;
                }
            }
        }
    }

    /**
     * Release the distributed lock.
     * @param productId
     */
    public void releaseDistributedLock(int threadId, int productId) {
        String path = "/product-lock-" + productId;
        try {
            zookeeper.delete(path, -1);
            logger.info("Thread " + threadId + " released the lock for product [id = " + productId + "] ......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * The ZooKeeper session watcher.
     */
    private class ZooKeeperWatcher implements Watcher {

        public void process(WatchedEvent event) {
            // logger.info("Received watched event: " + event.getType());
            if (KeeperState.SyncConnected == event.getState()) {

                if (EventType.None == event.getType() && null == event.getPath()) {
                    connectedSemaphore.countDown();
                } else if (event.getType() == EventType.NodeDeleted) {
                    if (latch != null) {
                        latch.get().countDown();
                    }
                }
            }
        }
    }

    /**
     * The inner class that encapsulates the singleton instance.
     */
    private static class Singleton {

        private static ZooKeeperSession instance;

        static {
            instance = new ZooKeeperSession();
        }

        public static ZooKeeperSession getInstance() {
            return instance;
        }
    }

    /**
     * Get the instance.
     * @return the singleton ZooKeeperSession instance
     */
    public static ZooKeeperSession getInstance() {
        return Singleton.getInstance();
    }

    public static void main(String[] args) {
        ZooKeeperSession zkSession = ZooKeeperSession.getInstance();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            new Thread(() -> {
                logger.info("Thread " + j + " started...");
                zkSession.acquireDistributedLock(j, 1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                zkSession.releaseDistributedLock(j, 1);
            }).start();
        }
    }
}