package org.chris.study.zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class DataMonitor {

    private ZooKeeper zk;

    private String znode;

    private Watcher chainedWatcher;

    protected boolean dead;

    private DataMonitor.DataMonitorListener listener;

    public DataMonitor(ZooKeeper zk, String znode, Watcher chainedWatcher, DataMonitorListener listener) {
        this.zk = zk;
        this.znode = znode;
        this.chainedWatcher = chainedWatcher;
        this.listener = listener;
    }

    public interface DataMonitorListener {

    }

}
