package aaa.concurrency.test1.cal;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-16 上午12:07
 */
public class TestCalculator {

    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int j = 0; j < 10000; j++) {
            int[] arr = IntStream.rangeClosed(1, 10000).toArray();
            Calculator cal = new ForLoopCalculator();
            sum = cal.sumUp(arr);
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis()-start);
    }

    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int j = 0; j < 10000; j++) {
            int[] arr = IntStream.rangeClosed(1, 10000).toArray();
            Calculator cal = new CustomizedCalculator();
            sum = cal.sumUp(arr);
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis()-start);
    }

    @Test
    public void test3() {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int j = 0; j < 10000; j++) {
            int[] arr = IntStream.rangeClosed(1, 10000).toArray();
            Calculator cal = new ForkJoinCalculator();
            sum = cal.sumUp(arr);
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis()-start);
    }

    @Test
    public void test4() {
        ExecutorService exec = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 3; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("测试用的！");
                }
            });
        }
    }

}
