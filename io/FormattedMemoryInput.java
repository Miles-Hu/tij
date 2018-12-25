package io;//: io/FormattedMemoryInput.java
import java.io.*;
import java.util.concurrent.TimeUnit;

public class FormattedMemoryInput {
  public static void main(String[] args)
  throws IOException {
    try {
      DataInputStream in = new DataInputStream(
              new ByteArrayInputStream(BufferedInputFile.
                      read("/home/miles/learning/java/thinking_in_java/src/main/java/io/FormattedMemoryInput.java")
                      .getBytes()));
      while(true){
        System.out.print((char)in.readByte());
        TimeUnit.MILLISECONDS.sleep(50);
      }
    } catch(EOFException e) {
      System.err.println("End of stream");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
} /* (Execute to see output) *///:~
