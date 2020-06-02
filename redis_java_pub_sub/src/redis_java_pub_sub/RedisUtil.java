package redis_java_pub_sub;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @描述 : 利用连接池连接单个redis工具
 * @创建时间： 2017年2月17日上午10:52:50
 *
 */
public class RedisUtil {

	/**
	 * @描述 : 初始化redis连接池
	 * @创建时间： 2017年2月18日下午5:24:46
	 *
	 * @return
	 * @throws IOException
	 */
	public static JedisPool initPool() throws IOException {
		
		System.out.println("1\n");
		// 初始化redis连接池配置
		JedisPoolConfig config = new JedisPoolConfig();
		System.out.println("2\n");
		config.setMaxTotal(1000);
		config.setMaxIdle(1000);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(false);
		System.out.println("3\n");
		
		// 初始化redis连接池
		JedisPool pool = new JedisPool(config,"192.168.206.173",6379,2000);
		System.out.println("4\n");		
		
		System.out.println("4.1\n");
		return pool;
	}
}