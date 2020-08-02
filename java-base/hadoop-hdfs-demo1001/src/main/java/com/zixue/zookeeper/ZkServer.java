package com.zixue.zookeeper;

import org.apache.zookeeper.*;

import com.zixue.util.ConfigurationUtil;

public class ZkServer {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ZkDemo.class);
	private static  String connect =
    		ConfigurationUtil.getString("zookeeper.connect", "hadoop108:2181,hadoop109:2181,hadoop110:2181,hadoop111:2181");
    private static int timeout = ConfigurationUtil.getInt("zookeeper.timeout", 2000);
    private static ZooKeeper zooKeeper = null;
    private static String parentPath = "/servers";

    public static void main(String[] args) throws Exception {
        //获取zookeeper的客户端
        getClient();
        //启动注册
        registServer(args[0]);
        //业务逻辑
        business(args[0]);
    }

    private static void business(String hostname) throws InterruptedException {
        logger.info(hostname + " is working...");
        Thread.sleep(Long.MAX_VALUE);
    }

    private static void registServer(String hostname) throws KeeperException, InterruptedException {
        //创建临时节点
        String path = zooKeeper.create(
                parentPath + "/server",
                hostname.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        logger.info(hostname + " is online " + path);
    }

    private static void getClient() throws Exception {
        zooKeeper = new ZooKeeper(connect, timeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                logger.info(event.getType() + "---" + event.getPath());
            }
        });
    }
}
