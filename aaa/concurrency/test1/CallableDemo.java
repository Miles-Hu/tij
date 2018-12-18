package aaa.concurrency.test1;


import java.util.concurrent.*;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-9 下午10:38
 */
public class CallableDemo {



    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService fjPool = Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors() * 2);
        Future<Integer> i = fjPool.submit(new AddTwoNumbers());
        Integer integer = i.get();
        Future<?> l = fjPool.submit(new AddTwoLong());
        System.out.println(l.get());
        System.out.println(integer);
    }

}

class AddTwoNumbers implements Callable<Integer>{

    public Integer call() throws Exception {
        return 3+4;
    }
}

class AddTwoLong implements Runnable{

    @Override
    public void run() {
        System.out.println(7+7);
    }
}
