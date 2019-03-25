package aaa.dynamic.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 19-1-22 下午7:28
 */
public class TestDrive {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Log.class);
        enhancer.setCallback(new LogMethodInterceptor());

        Log log = (Log)enhancer.create();
        log.log("Hello Miles !");
    }

}
