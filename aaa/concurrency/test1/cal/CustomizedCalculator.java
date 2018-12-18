package aaa.concurrency.test1.cal;

import generics.Functional;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-16 上午12:15
 */
public class CustomizedCalculator implements Calculator{

    int parallism;
    ExecutorService exec ;

    CustomizedCalculator() {
        parallism = Runtime.getRuntime().availableProcessors();
        exec = Executors.newWorkStealingPool(parallism);
    }


    @Override
    public int sumUp(int[] arr) {

        int sum = 0;
        int parts = arr.length / parallism;
        ArrayList<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < parallism; i++) {
            int from = i*parts;
            int to = (i+1)*parts-1;
            futures.add(exec.submit(new CalTask(arr, from, to)));
        }

        for (Future<Integer> result : futures) {
            try {
                sum += result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        exec.shutdownNow();
        return sum;
    }

    private class CalTask implements Callable<Integer> {

        int[] arr;
        int from;
        int to;

        public CalTask(int[] arr, int from, int to) {
            this.arr = arr;
            this.from = from;
            this.to = to;
        }

        @Override
        public Integer call() throws Exception {

            int sum = 0;
            for (int i = from; i <= to; i++) {
                sum += arr[i];
            }

            return sum;
        }
    }
}
