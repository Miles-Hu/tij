package aaa.concurrency.test2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo2 {

    public static AtomicReference<User> atomicUserRef = new AtomicReference<User>();

    public static void main(String[] args) throws InterruptedException {
        User user = new User("zejian", 18);
        atomicUserRef.set(user);
        User updateUser = new User("Shine", 25);

        new Thread(()->atomicUserRef.set(new User("Hello",220))).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(atomicUserRef.getAndSet(updateUser));

        System.out.println(atomicUserRef.get().toString());  //执行结果:User{name='Shine', age=25}
    }

    static class User {
        public String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}