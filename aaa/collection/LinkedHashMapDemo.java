package aaa.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-26 上午1:36
 */
public class LinkedHashMapDemo {

    public static void main(String[] args) {

        LinkedHashMap<String, Integer> lmap = new LinkedHashMap<String, Integer>();
        lmap.put("语文", 1);
        lmap.put("数学", 2);
        lmap.put("英语", 3);
        lmap.put("历史", 4);
        lmap.put("政治", 5);
        lmap.put("地理", 6);
        lmap.put("生物", 7);
        lmap.put("化学", 8);
        for(Map.Entry<String, Integer> entry : lmap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
