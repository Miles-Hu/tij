package aaa.digester.demo1;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 19-1-15 下午5:28
 */
public class TestDigester {
    public static void main(String[] args)
    {

        try
        {
            //1、创建Digester对象实例
            Digester digester = new Digester();

            //2、配置属性值
            digester.setValidating(false);

            //3、push对象到对象栈
            //digester.push(new Foo());

            //4、设置匹配模式、规则
            digester.addObjectCreate("foo", "aaa.digester.demo1.Foo");
            digester.addSetProperties("foo");
            digester.addObjectCreate("foo/bar", "aaa.digester.demo1.Bar");
            digester.addSetProperties("foo/bar");
            digester.addSetNext("foo/bar", "addBar", "aaa.digester.demo1.Bar");

            //5、开始解析
            Foo foo = (Foo)digester.parse(new FileInputStream(new File("example.xml")));

            //6、打印解析结果
            System.out.println(foo.getName());
            for (Bar bar : foo.getBarList())
            {
                System.out.println(bar.getId() + "," + bar.getTitle());
            }

        }
        catch (IOException e)
        {

            e.printStackTrace();
        }
        catch (SAXException e)
        {

            e.printStackTrace();
        }
    }
}
