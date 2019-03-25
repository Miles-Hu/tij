package aaa.dynamic.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 19-1-22 下午7:26
 */
public class LogMethodInterceptor implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if ("log".equals(method.getName())) {
            System.out.println("you are invoking log method!");
        }
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("method invoked by CGLIB");
        return result;
    }

}
