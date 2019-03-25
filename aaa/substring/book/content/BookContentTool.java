package aaa.substring.book.content;


import org.junit.Test;

import java.io.*;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 19-1-22 下午11:40
 */
public class BookContentTool {

    @Test
    public void subStringBookContent() {
        String inPath = "/home/miles/VirtualBox VMs/windows10/sharefolder/Hello Miles.txt";
        String outPath = "/home/miles/VirtualBox VMs/windows10/sharefolder/Hello Miles1.txt";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(inPath)));
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(outPath))) {
            String len = null;
            while ((len = bufferedReader.readLine()) != null) {
                len = len.trim();
                if (len.matches(".*\\d\\d\\d$")){
                    len = len.substring(0, len.length() - 3);
                }else if(len.matches(".*\\d\\d$")){
                    len = len.substring(0, len.length() - 2);
                }if(len.matches(".*\\d$")){
                    len = len.substring(0, len.length() - 1);
                }else if(len.length()>=6 && ('.'==(len.charAt(3)) || '.'==(len.charAt(4)) || (len.charAt(4)+"").matches("\\d") ||(len.charAt(5)+"").matches("\\d"))){
                    len = "\t"+len;
                }
                if ("".equals(len)) {
                    continue;
                }
                printWriter.println(len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSimpleBookContext() {
        String inPath = "/home/miles/VirtualBox VMs/windows10/sharefolder/Hello Miles.txt";
        String outPath = "/home/miles/VirtualBox VMs/windows10/sharefolder/Hello Miles1.txt";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(inPath)));
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(outPath))) {
            String len = null;
            while ((len = bufferedReader.readLine()) != null) {

                if(!"第".equals(len.charAt(2))){
                    continue;
                }
                printWriter.println(len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
