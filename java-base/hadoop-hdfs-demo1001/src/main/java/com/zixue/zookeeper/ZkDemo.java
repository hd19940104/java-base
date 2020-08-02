package com.zixue.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import com.zixue.util.ConfigurationUtil;

import java.io.IOException;
import java.util.List;

public class ZkDemo {
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ZkDemo.class);
    private  String connect =
    		ConfigurationUtil.getString("zookeeper.connect", "hadoop108:2181,hadoop109:2181,hadoop110:2181,hadoop111:2181");
    private int timeout = ConfigurationUtil.getInt("zookeeper.timeout", 2000);
    private ZooKeeper zooKeeper = null;

    //获取zookeeper的客户端
    @Before
    public void getClient() throws Exception {
        zooKeeper = new ZooKeeper(connect, timeout, new Watcher() {
            //接收到zookeeper发来的通知以后，做出的处理措施（自己的处理的业务逻辑）
            @Override
            public void process(WatchedEvent event) {
                logger.info(event.getType() + "----" + event.getPath());
//                try {
//                    List<String> children = zooKeeper.getChildren("/zixue", true);
//                    for (String child : children){
//                        logger.info(child);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });
    }
    //创建节点
    @Test
    public void testCreate() throws KeeperException, InterruptedException {
        String path = zooKeeper.create(
                "/zixue/0815", "zixue-0815".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        logger.info(path);
    }

    /**
     * 查看节点是否存在
     */
    @Test
    public void testExist() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists("/zixue", false);
        logger.info(stat == null ? "not exist":"exist");
    }

    /**
     * 查看子节点
     */
    @Test
    public void testList() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/zixue", true);
        for (String child : children){
            logger.info(child);
            
        }
        Thread.sleep(Long.MAX_VALUE);
    }
    /**
     * 改变节点的内容
     */
    @Test
    public void testSet() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.setData("/zixue/0815", "i love 0815".getBytes(), -1);
    }
    /**
     * 获取节点数据
     */ 
    @Test
    public void testGet() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData("/zixue", true, null);
        logger.info(new String(data));
        Thread.sleep(Long.MAX_VALUE);
    }
}
