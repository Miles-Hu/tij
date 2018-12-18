package aaa.concurrency.test1.cal;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 18-12-16 上午12:06
 */
public class ForLoopCalculator implements Calculator {

    @Override
    public int sumUp(int[] arr) {

        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }
}
