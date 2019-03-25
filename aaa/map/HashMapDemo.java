package aaa.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 19-3-21 上午8:21
 */
public class HashMapDemo {

    public static void main(String[] args) {
        Random random = new Random(47);
        int count = 5000000;
        Map<String, String> map1 = new ConcurrentHashMap<>();
        Map<String, String> map2 = new HashMap<>();
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            String key = random.nextInt(count) + "";
            map1.put(key, key);
        }
        for (int i = 0; i < count; i++) {
            String key = random.nextInt(count) + "";
            map1.get(key);
        }
        System.out.println(System.currentTimeMillis()-start1);

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            String key = random.nextInt(count) + "";
            map2.put(key, key);
        }
        for (int i = 0; i < count; i++) {
            String key = random.nextInt(count) + "";
            map2.get(key);
        }
        System.out.println(System.currentTimeMillis()-start2);

    }

}
