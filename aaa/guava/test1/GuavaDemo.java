package aaa.guava.test1;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import sun.instrument.InstrumentationImpl;

import java.lang.instrument.Instrumentation;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 19-1-3 上午11:05
 */
public class GuavaDemo {

    //设置最大存储
    @Test
    public void test1() {
        Cache<String, String> guavaCache = CacheBuilder.newBuilder().maximumSize(3).build();
        guavaCache.put("101","hello 101");
        guavaCache.put("202","hello 202");
        guavaCache.put("303","hello 303");
        guavaCache.put("404","hello 404");
        String ifPresent = guavaCache.getIfPresent("101");
        System.out.println(ifPresent);
        ImmutableMap<String, String> allPresent = guavaCache.getAllPresent(Arrays.asList("101", "202", "303", "404"));
        System.out.println(allPresent);
        System.out.println(guavaCache.getClass().getClassLoader());
    }

    //设置过期时间
    @Test
    public void test2() throws InterruptedException {
        Cache<String, String> expireCache = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS).build();
        expireCache.put("key","value");
        for (int i = 1; i <= 5; i++) {
            System.out.println("第"+i+"秒后key的值为："+expireCache.getIfPresent("key"));
            TimeUnit.SECONDS.sleep(1);
        }

        Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterAccess(3, TimeUnit.SECONDS).build();
        cache.put("miles","handsom");
        for (int i = 1; i <= 5; i++) {
            System.out.println("暂停"+i+"秒访问后miles的值为："+cache.getIfPresent("miles"));
            TimeUnit.SECONDS.sleep(i);
        }
    }

    //显示清除
    @Test
    public void test3() {
        Cache<String, String> guavaCache = CacheBuilder.newBuilder().build();
        guavaCache.put("key1","value1");
        guavaCache.put("key2","value2");
        guavaCache.put("key3","value3");
        guavaCache.invalidateAll(Arrays.asList("key2","key3"));
        System.out.println(guavaCache.getAllPresent(Arrays.asList("key1","key2","key3")));
    }

}
