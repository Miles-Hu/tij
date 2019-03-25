package aaa.dynamic.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 19-1-22 下午7:02
 */
public class LogInvocationHandler implements InvocationHandler {

    private Log log;

    public LogInvocationHandler(Log log) {
        this.log = log;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if ("log".equals(method.getName())) {
            System.out.println("You are invoking log method!");
        }
        Object result = method.invoke(log, args);
        System.out.println("method invoked by proxy");

        return result;
    }
}
