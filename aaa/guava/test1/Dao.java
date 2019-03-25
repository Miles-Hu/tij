package aaa.guava.test1;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 19-1-3 上午11:09
 */
public class Dao {

    final Cache<String, String> guavaCache = CacheBuilder.newBuilder().build();


    public String getFromGuava(final String key) throws ExecutionException {
        String value = guavaCache.get(key, new Callable<String>() {
            public String call() throws Exception {
                System.out.println("模仿从数据库中读取数据，睡眠一秒");
                TimeUnit.SECONDS.sleep(1);
                return getFromDB(key);
            }
        });
        return value;
    }

    private String getFromDB(String key) {
        System.out.println("正在从数据库读取数据！");
        if("101".equals(key))
            return "Hello, i am 101's value. ^_^";
        if("202".equals(key))
            return "Hello, 202, i am waiting for you ^_^";
        return "";
    }

    public static void main(String[] args) throws ExecutionException {
        Dao dao = new Dao();
        for (int i = 0; i < 3; i++) {
            System.out.println(dao.getFromGuava("101"));
            System.out.println(dao.getFromGuava("202"));
            System.out.println(dao.getFromGuava("303"));
        }
    }

}
