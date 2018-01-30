package com.xiao;

import com.xiaoxz.util.RedisUtil;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/30
 * @Modified by :
 **/

public class JedisTemplate {


    private Jedis jedis;
    @Before
    public void init() {
        jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
        jedis.connect();
    }

    @Test
    public void flushAll() {
        jedis.flushAll();
    }

    /**
    *  @descp:方法作用描述
    *  @Author:xiaoxz
    *  @date:2018/1/30
     * @params:将哈希表中key1中的域field设置为value值
    */
    @Test
    public void hset() {
        jedis.hset("key1", "field1", "value1");
        jedis.hset("key1", "field2", "value2");
        jedis.hset("key1", "field3", "value3");
        jedis.disconnect();
    }

    /**
    *  @descp:方法作用描述
    *  @Author:xiaoxz
    *  @date:2018/1/30
     * @params:No 将哈希表中key1中的域field设置为value值
    */
    @Test
    public void hmset() {
        Map<String, String> hMap = new HashMap<>();
        hMap.put("field1", "map1");
        hMap.put("field2", "map2");
        hMap.put("field3", "map3");
        jedis.hmset("key1", hMap);
        jedis.disconnect();
    }



    @Test
    public void hgetValue() {
        Map<String, String> hMap = jedis.hgetAll("key1");
        for(Map.Entry<String, String> entry : hMap.entrySet()) {
            System.out.println("key=" + entry.getKey() + ";value=" + entry.getValue());
        }
        jedis.disconnect();
    }

    @Test
    public void lpush() {
        jedis.lpush("lkey1", "value-1");
        jedis.lpush("lkey1", "value-2");
        jedis.lpush("lkey1", "value-3");
        jedis.lpush("lkey1", "value-4");
        jedis.disconnect();
    }

    @Test
    public void lrange() {
        List<String> list = jedis.lrange("lkey1", 0, -1);
        for(String v : list) {
            System.out.println(v);
        }
    }

    @Test
    public void testPool() {
        Jedis j  = RedisUtil.getJedis();
        List<String> list = j.hmget("key1", "field2");
        for(String v : list) {
            System.out.println(v);
        }
        RedisUtil.releaseJedis(j);
    }
}
