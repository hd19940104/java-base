package redis.clients.jedis;

/*
  这是一个很蛋疼的设计，因为难保证client超时返回，服务端没异常，连接如果复用会造成蹿包
 */

public class JedisPoolUtil extends JedisPool {
    private JedisPool pool;
    public JedisPoolUtil(JedisPool p){
        pool = p;
    }
    // 关闭连接（非放回连接池）
    public void returnBrokenResource(final Jedis jedis){
        pool.returnBrokenResource(jedis);
    }
}
