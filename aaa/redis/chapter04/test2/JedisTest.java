package aaa.redis.chapter04.test2;

import org.junit.Test;
import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-31 上午11:09
 */
public class JedisTest {

    JedisCluster cluster;

    {
        JedisPoolConfig conf = new JedisPoolConfig();
        conf.setMaxIdle(1);
        conf.setMaxTotal(10);
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("172.17.0.5", 6379));
        nodes.add(new HostAndPort("172.17.0.6", 6380));
        nodes.add(new HostAndPort("172.17.0.7", 6381));
        cluster = new JedisCluster(nodes,conf);
    }

    @Test
    public void test24() {
        System.out.println(cluster.set("miles", "hoo"));
        System.out.println(cluster.get("miles"));
        cluster.mset("{1}.jacob", "female", "{1}.selena", "female");
        System.out.println(cluster.mget("{1}.jacob","{1}.selena"));
    }

    @Test
    public void test27() {
        System.out.println(cluster.lpush("lfriend","rachel","ross"));
        System.out.println(cluster.rpush("lfriend","monica","joey"));
        System.out.println(cluster.lrange("lfriend",0l,-1l));
        System.out.println(cluster.lpop("lfriend"));
        System.out.println(cluster.rpop("lfriend"));
        System.out.println(cluster.lset("lfriend",1,"chandler"));
        System.out.println(cluster.lindex("lfriend",1));
        System.out.println(cluster.lrange("lfriend",0l,-1l));
    }

    @Test
    public void test28() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("rachel", "female");
        map.put("ross", "female");
        map.put("joey", "male");
        System.out.println(cluster.hmset("hfriend",map));
        System.out.println(cluster.hmget("hfriend", "rachel", "joey"));
        System.out.println(cluster.hexists("hfriend","rachel"));
        System.out.println(cluster.hgetAll("hfriend"));
        ScanResult<Map.Entry<String, String>> hfriend = cluster.hscan("hfriend", "0");
        System.out.println(hfriend.getResult());
    }

    @Test
    public void test24a() {
        cluster.sadd("ali", "java", "go", "c");
        System.out.println(cluster.sismember("ali","java"));
        System.out.println(cluster.sscan("ali",0).getResult());
    }

    @Test
    public void test6() {
        System.out.println(cluster.expire("ali",20));
        System.out.println(cluster.ttl("ali"));
        System.out.println(cluster.persist("ali"));
        System.out.println(cluster.pexpire("ali",1000));
        System.out.println(cluster.ttl("ali"));
    }

    @Test
    public void test22() {
        HashMap<String, Double> map = new HashMap<>();
        map.put("spring", 18d);
        map.put("summer",31d);
        map.put("fall", 19d);
        map.put("winter", 14d);
        System.out.println(cluster.zadd("seasons",map));
        System.out.println(cluster.zrange("seasons",0,-1));
    }

    @Test
    public void test23() {
        System.out.println(cluster.pfadd("{1}.day4","monica","rachel"));
        System.out.println(cluster.pfadd("{1}.day5","ross","joey"));
        System.out.println(cluster.pfadd("{1}.day6","monica","joey"));
        System.out.println(cluster.pfmerge("3days","{1}.day4","{1}.day5","{1}.day6"));
        System.out.println(cluster.pfcount("3days"));
    }

}
