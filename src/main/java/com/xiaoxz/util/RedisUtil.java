package com.xiaoxz.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/30
 * @Modified by :
 **/
public class RedisUtil {

    private static String addr = "127.0.0.1";
    private static int port = 6379;
    private static String auth = "123456";
    private static int max_active = 200;
    private static int max_idel = 200;
    private static int max_await = 1000;
    private static int timeout = 1000;
    private static boolean test_borrow = true;
    private static JedisPool jedisPool;

    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxActive(max_active);
            config.setMaxIdle(max_idel);
            config.setTestOnBorrow(test_borrow);
            config.setMaxWait(max_await);
            jedisPool = new JedisPool(config, addr, port, timeout, auth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    *  @descp:方法作用描述
    *  @Author:xiaoxz
    *  @date:2018/1/30
     * @params:获取jedsi实例
    */
    public static Jedis getJedis() {
        try {
            if(jedisPool != null) {
                Jedis jedis = jedisPool.getResource();
                return jedis;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
    *  @descp:方法作用描述
    *  @Author:xiaoxz
    *  @date:2018/1/30
     * @params:释放jedis资源
    */
    public static void releaseJedis(final Jedis jedis) {
        if(jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}
