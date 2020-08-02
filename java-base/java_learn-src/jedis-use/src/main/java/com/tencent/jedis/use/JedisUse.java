package com.tencent.jedis.use;


import org.apache.log4j.Logger;

public class JedisUse {
    public static Logger logger = Logger.getLogger(JedisUse.class);

    public static void main(String[] args){
        JedisUtil jedis = new JedisUtil();
        jedis.set("hello", "world");
        String res = jedis.get("hello");
        System.out.println("res = " + res);

        logger.info("res = " + res);
    }
}
