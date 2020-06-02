package redis_java_pub_sub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class SubThread extends Thread {
	
    private final JedisPool jedisPool;
    private final Subscriber subscriber;

    private final String channel = "mychannel";

    public SubThread(JedisPool jedisPool,Subscriber subscriber) {
        super("SubThread");
        this.jedisPool = jedisPool;
        this.subscriber=subscriber;
    }

    @Override
    public void run() {
        System.out.println(String.format("订阅redis中,频道 %s,线程被阻塞", channel));
        Jedis jedis = null;
        try {
        	System.out.println("5\n");
            jedis = jedisPool.getResource();   //取出一个连接
            System.out.println("6\n");
            jedis.subscribe(subscriber, channel);//通过subscribe 的api去订阅，入参是订阅者和频道名
            System.out.println("7\n");
        } catch (Exception e) {
            System.out.println(String.format("订阅频道失败, %s", e));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
