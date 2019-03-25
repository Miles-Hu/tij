package aaa.concurrency.test2;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    //创建AtomicInteger,用于自增操作
    static AtomicInteger i=new AtomicInteger();
    //static int i;

    public static class AddThread implements Runnable{
        public void run(){
           for(int k=0;k<10000;k++)
               i.incrementAndGet();
               //i++;
        }

    }
    public static void main(String[] args) throws InterruptedException {
        Thread[] ts=new Thread[10];
        //开启10条线程同时执行i的自增操作
        for(int k=0;k<10;k++){
            ts[k]=new Thread(new AddThread());
        }
        //启动线程
        for(int k=0;k<10;k++){ts[k].start();}

        for(int k=0;k<10;k++){ts[k].join();}

        System.out.println(i);//输出结果:100000，如果改成i++的话，结果会少于100000
    }
}