package com.zixue.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import com.zixue.util.ConfigurationUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZkClient {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ZkDemo.class);
	private static  String connect =
    		ConfigurationUtil.getString("zookeeper.connect", "hadoop108:2181,hadoop109:2181,hadoop110:2181,hadoop111:2181");
    private static int timeout = ConfigurationUtil.getInt("zookeeper.timeout", 2000);
    private static ZooKeeper zooKeeper = null;
    private static String parentPath = "/servers";

    public static void main(String[] args) throws Exception {
        //获取zookeeper的客户端
        getClient();
        //获取服务器列表(主机名),并监听
        getServers();
        //业务逻辑
        business();
    }
    private static void business() throws InterruptedException {
        logger.info("Client is working...");
        Thread.sleep(Long.MAX_VALUE);
    }

    private static void getServers() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren(parentPath, true);
        //用来装服务器的主机名
        ArrayList<String> hosts = new ArrayList<>();
        for (String child : children) {
            byte[] data = zooKeeper.getData(parentPath + "/" + child, false, null);
            hosts.add(new String(data));
        }
        logger.info(hosts);
    }

    private static void getClient() throws Exception {
        zooKeeper = new ZooKeeper(connect, timeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                logger.info(event.getType() + "-----" + event.getPath());
                //重新获取客户端的列表
                try {
                    getServers();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
