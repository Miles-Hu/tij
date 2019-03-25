package aaa.concurrency.test2;

public class StringBufferRemoveSync {

    public void add(String str1, String str2) {
        //StringBuffer是线程安全,由于sb只会在append方法中使用,不可能被其他线程引用
        //因此sb属于不可能共享的资源,JVM会自动消除内部的锁
        StringBuffer sb = new StringBuffer();
        sb.append(str1).append(str2);
    }

    StringBuffer sb1 = new StringBuffer();

    public void add1(String str1, String str2) {
        sb1.append(str1).append(str2);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        StringBufferRemoveSync rmsync = new StringBufferRemoveSync();
        for (int i = 0; i < 100000000; i++) {
            rmsync.add("abc", "123");
        }
        System.out.println(System.currentTimeMillis()-start);

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            rmsync.add1("abc", "123");
        }
        System.out.println(System.currentTimeMillis()-start2);
    }

}