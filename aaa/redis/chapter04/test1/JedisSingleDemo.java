package aaa.redis.chapter04.test1;

import org.junit.Test;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class JedisSingleDemo {

    Jedis jedis = new Jedis("127.0.0.1", 6379);

    //JedisPool pool = new JedisPool("127.0.0.1",6379);

    @Test
    public void test24() {
        jedis.set("miles", "male");
        jedis.mset("max", "male", "selena", "female");
        List<String> mget = jedis.mget("miles", "max", "selena");
        System.out.println(mget);
    }

    @Test
    public void test27() {
        jedis.lpush("Friends", "Monica", "Rachel");
        jedis.rpush("Friends", "Ross", "Joey");
        List<String> friends = jedis.lrange("Friends", 0, -1);
        String rachel = jedis.lpop("Friends");
        String joey = jedis.rpop("Friends");
        jedis.lset("Friends", 1, "Pheebe");
        String pheebe = jedis.lindex("Friends", 1);
        System.out.printf("%s %s %s %s \n",friends,rachel,joey,pheebe);
    }

    @Test
    public void test28() {
        jedis.hset("F_Gender", "Monica", "female");
        HashMap<String, String> map = new HashMap<>();
        map.put("Ross", "male");
        map.put("Rachel", "female");
        jedis.hmset("F_Gender", map);
        List<String> map1 = jedis.hmget("F_Gender","Monica","Ross","Rachel");
        System.out.println(map1);

        Boolean monica = jedis.hexists("F_Gender", "Monica");
        System.out.println("Monica: "+monica);
        if (monica) {
            jedis.hdel("F_Gender", "Monica");
        }
        Map<String, String> all = jedis.hgetAll("F_Gender");
        for (String s : all.keySet()) {
            System.out.printf("key=%s, value=%s \n",s,all.get(s));
        }
    }

    @Test
    public void test24c() {
        jedis.sadd("miles", "handsom", "young", "programmer");
        Boolean sismember = jedis.sismember("miles", "handsom");
        System.out.printf("sismember=%s \n",sismember);
        Set<String> miles = jedis.smembers("miles");
        System.out.println(miles);
    }

    @Test
    public void test22() {
        HashMap<String, Double> map = new HashMap<>();
        map.put("Jan", 19d);
        map.put("Jun", 21d);
        map.put("Sep", 29d);
        map.put("Dec", 17d);
        jedis.zadd("temperature", map);
        Set<String> temperature = jedis.zrange("temperature", 0, -1);
        System.out.println(temperature);
    }

    @Test
    public void test23() {
        jedis.pfadd("day1", "monica", "ross");
        jedis.pfadd("day2", "joey", "chandler");
        jedis.pfadd("day3", "monica", "chandler");
        jedis.pfmerge("3days", "day1", "day2", "day3");
        long pfcount = jedis.pfcount("3days");
        System.out.println(pfcount);
    }

    @Test
    public void test25() {
        HashMap<String, GeoCoordinate> map = new HashMap<>();
        map.put("miles", new GeoCoordinate(-1d, 3d));
        map.put("selena", new GeoCoordinate(3d, 2d));
        map.put("justin", new GeoCoordinate(5d, 1d));
        map.put("taylor", new GeoCoordinate(7d, 2d));
        map.put("camila", new GeoCoordinate(6d, 5d));
        jedis.geoadd("people", map);
        List<GeoCoordinate> geopos = jedis.geopos("people", "camila");
        System.out.println(geopos);

        List<GeoRadiusResponse> people = jedis.georadius("people", 1d, 1d, 1000, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist().sortAscending());
        for (GeoRadiusResponse r : people) {
            System.out.printf("mem=%s \tdist=%s \n",r.getMemberByString(),r.getDistance());
        }

        List<GeoRadiusResponse> miles = jedis.georadiusByMember("people", "miles", 1000, GeoUnit.KM,GeoRadiusParam.geoRadiusParam().withDist().sortAscending());
        for (GeoRadiusResponse r : miles) {
            System.out.printf("mem=%s \tdist=%s \n",r.getMemberByString(),r.getDistance());
        }

        Double geodist = jedis.geodist("people", "miles", "selena");
        System.out.println(geodist);
    }

    @Test
    public void test26() {
        long size = jedis.dbSize();
        System.out.println(size);
        ScanResult<String> scan = jedis.scan(0, new ScanParams().count((int)size));
        System.out.println(scan.getResult());
        jedis.del("selena");
        System.out.println(jedis.dbSize());

        Boolean miles = jedis.exists("miles");
        System.out.println("miles exists? "+miles);
        if (miles) {
            System.out.println(jedis.type("miles"));
        }
        jedis.rename("miles", "Miles");
        System.out.println(jedis.sscan("Miles",0).getResult());
    }

    @Test
    public void test33() {
        jedis.setbit("ordered", 123l, true);
        jedis.setbit("ordered", 23l, true);
        jedis.setbit("reserved", 100l, true);
        jedis.setbit("reserved", 23l, true);
        Boolean ordered = jedis.getbit("ordered", 23);
        System.out.println(ordered);
        Long ordered1 = jedis.bitcount("ordered");
        System.out.println(ordered1);

        jedis.bitop(BitOP.AND, "orderedAndReserved", "ordered", "reserved");
        Long orderedAndReserved = jedis.bitcount("orderedAndReserved");
        System.out.println(orderedAndReserved);
    }

    @Test
    public void test34() {
        jedis.sadd("numbers", "3", "2", "4", "1", "5");
        jedis.sadd("seasons", "spring", "summer", "fall", "winter");
        List<String> numbers = jedis.sort("numbers");
        System.out.println(numbers);
        List<String> seasons = jedis.sort("seasons", new SortingParams().alpha().limit(0, 3));
        System.out.println(seasons);
        jedis.set("spring_temp", "19");
        jedis.set("summer_temp", "30");
        jedis.set("fall_temp", "28");
        jedis.set("winter_temp", "16");
        List<String> seasons1 = jedis.sort("seasons", new SortingParams().by("*_temp"));
        System.out.println(seasons1);
    }

    @Test
    public void test34a() {
        jedis.expire("spring_temp", 20);
        while (true) {
            Long time = jedis.ttl("spring_temp");
            System.out.println(time);
            if (time == 0) {
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        jedis.pexpire("seasons",20000);
        while (true) {
            Long time = jedis.ttl("seasons");
            if (time == 1) {
                System.out.println(time);
                jedis.persist("seasons");
                break;
            }
        }
        System.out.println(jedis.exists("seasons"));
    }

    @Test
    public void test34b() {
        Transaction trans = jedis.multi();
        Response<String> set = trans.set("jacob", "male");
        Response<Long> jacob = trans.incr("jacob");
        Response<String> jacob1 = trans.get("jacob");
        trans.exec();
        System.out.println(jacob1.get());
        try {
            System.out.println(jacob.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(set.get());
    }

    @Test
    public void testPip() {
        Pipeline pip = jedis.pipelined();
        pip.set("joseph", "male");
        pip.hset("animal", "dog", "livstock");
        pip.sync();
    }

}
