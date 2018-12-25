package aaa.concurrency.test1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-23 下午5:48
 */
public class TestThread3 {

    private static BlockingQueue aToB = new ArrayBlockingQueue(1);
    private static BlockingQueue bToC = new ArrayBlockingQueue(1);
    private static BlockingQueue cToA = new ArrayBlockingQueue(1);
    public static final int NUM = 100;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= NUM; i++) {
                    try {
                        aToB.put(new Object());
                        System.out.println("第"+i+"次");
                        System.out.println("A");
                        cToA.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= NUM; i++) {
                    try {
                        aToB.take();
                        System.out.println("B");
                        bToC.put(new Object());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= NUM; i++) {
                    try {
                        bToC.take();
                        System.out.println("C");
                        cToA.put(new Object());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
