package aaa.dynamic.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 19-1-22 下午7:01
 */
public class TestDrive {

    public static void main(String[] args) {
        Log log = (Log)Proxy.newProxyInstance(TestDrive.class.getClassLoader(),
                new Class[]{Log.class},
                new LogInvocationHandler(new LogImpl()));

        log.log("Hello Miles!");
    }

}
