package com.tencent.jedis.use;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPoolUtil;

public class JedisUtil {
    private JedisPool pool = null;
    private JedisPoolUtil poolUtil = null;
    public JedisUtil(){
        this.pool = new JedisPool(new JedisPoolConfig(), JedisConf.redisIP);
        this.poolUtil = new JedisPoolUtil(pool);
    }

    public Long expire(String key, int exTime) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            //设置有效期
            res = jedis.expire(key, exTime);
        } catch (Exception e) {
            System.out.println("expire key: " + key + " error" +  e);
            poolUtil.returnBrokenResource(jedis);
            return null;
        }finally {
            if (jedis != null)
            {
                jedis.close(); // 不会真正的关闭，会存redis
            }
        }
        return res;
    }

    public String setEx(String key, String value, int exTime) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = pool.getResource();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            System.out.println("setex key: " + key + " error" +  e);
            poolUtil.returnBrokenResource(jedis);
            return null;
        }
        finally {
            if (jedis != null)
            {
                jedis.close(); // 不会真正的关闭，会存redis
            }
        }
        return result;
    }


    public String set(String key, String value) {
        Jedis jedis = null;
        //jedis返回的结果
        String result = null;
        try {
            jedis = pool.getResource();
            //设置key-value
            result = jedis.set(key, value);
        } catch (Exception e) {
            System.out.println("set key: " + key + " error" +  e);
            poolUtil.returnBrokenResource(jedis);
            return null;
        }finally {
            if (jedis != null)
            {
                jedis.close(); // 不会真正的关闭，会存redis
            }
        }
        return result;
    }

    public String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = pool.getResource();
            //根据key获取value值
            result = jedis.get(key);
        } catch (Exception e) {
            System.out.println("setex key: " + key + " error" +  e);
            poolUtil.returnBrokenResource(jedis);
            return null;
        }finally {
            if (jedis != null)
            {
                jedis.close(); // 不会真正的关闭，会存redis
            }
        }
        return result;
    }

    public Long del(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = pool.getResource();
            //根据key删除key-value
            result = jedis.del(key);
        } catch (Exception e) {
            System.out.println("set key: " + key + " error: " + e);
            poolUtil.returnBrokenResource(jedis);
            return null;
        }finally {
            if (jedis != null)
            {
                jedis.close();
            }
        }
        return result;
    }
}
