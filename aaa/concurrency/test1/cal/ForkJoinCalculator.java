package aaa.concurrency.test1.cal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-16 上午12:31
 */
public class ForkJoinCalculator implements Calculator {



    @Override
    public int sumUp(int[] arr) {
        ForkJoinPool exec = (ForkJoinPool)Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors());

        Integer sum = exec.invoke(new CalTask(arr, 0, arr.length-1));

        exec.shutdownNow();
        return sum;
    }

    private class CalTask extends RecursiveTask<Integer> {

        int[] arr;
        int from;
        int to;

        CalTask(int[] arr,int from, int to) {
            this.arr = arr;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Integer compute() {
            int sum = 0;

            if (to - from < 50) {
                sum = 0;
                for (int j = from; j <= to; j++) {
                    sum += arr[j];
                }
                return sum;
            }else {
                int middle = (to+from)/2;
                CalTask left = new CalTask(arr, from, middle);
                CalTask right = new CalTask(arr, middle + 1, to);
                left.fork();
                right.fork();
                return left.join()+right.join();
            }
        }
    }

}
