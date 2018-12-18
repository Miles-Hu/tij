package aaa.concurrency.test1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-9 下午10:55
 */
public class DelayQueueDemo {

    static ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    ExecutorService executorService = Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors() * 1);

    @Test
    public void test1() {
        for (int i : arr) {
            if (i == 3) {
                new Thread(new Runnable() {
                    public void run() {
                        arr.add(6);
                    }
                }).start();
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(i);
        }
    }

    @Test
    public void test2() {
        /*ExecutorService fjpool = Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors() * 2);
        Future<?> hello = fjpool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println("Hello");
                } catch (InterruptedException e) {
                    System.out.println("Interrupted1");
                    Thread.currentThread().interrupt();
                    System.out.println("Interrupted2");
                }
            }
        });*/
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println("Hello");
                } catch (InterruptedException e) {
                    System.out.println("Interrupted1");
                    Thread.currentThread().interrupt();
                    System.out.println("Interrupted2");
                }
            }
        });
        t.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*hello.cancel(true);*/
        t.interrupt();
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(20);
                return 20;
            }
        };
        FutureTask<Integer> ft = new FutureTask<>(callable);
        new Thread(ft).start();
        System.out.println(ft.get());
    }

    @Test
    public void test4() throws InterruptedException {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(17);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        TimeUnit.SECONDS.sleep(15);

    }
}
