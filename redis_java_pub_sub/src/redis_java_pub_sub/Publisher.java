package redis_java_pub_sub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Publisher extends Thread {
	
	private final JedisPool jedisPool;
	public String mess_pub;
	
	public Publisher(JedisPool jedisPool,String mess_main) {
		this.jedisPool=jedisPool;
		this.mess_pub=mess_main;
	}
	
	@Override
	public void run() {	
		
		Jedis jedis=jedisPool.getResource();
								    
				if(!mess_pub.equals(null))
				{
					jedis.publish("mychannel",mess_pub);									
				}
	}
}
