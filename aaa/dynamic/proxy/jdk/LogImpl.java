package aaa.dynamic.proxy.jdk;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 19-1-22 下午7:01
 */
public class LogImpl implements Log {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
