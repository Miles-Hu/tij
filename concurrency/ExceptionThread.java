package concurrency;//: concurrency/ExceptionThread.java
// {ThrowsException}
import java.util.concurrent.*;

public class ExceptionThread implements Runnable {
  public void run() {
    throw new RuntimeException();
  }
  public static void main(String[] args) throws InterruptedException {
    ExecutorService exec = Executors.newCachedThreadPool();
    exec.execute(new ExceptionThread());
    TimeUnit.MILLISECONDS.sleep(1000);
    System.out.println("Hello Miles!");
  }
} ///:~
