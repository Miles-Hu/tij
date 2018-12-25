package io;//: io/MemoryInput.java
import java.io.*;
import java.util.concurrent.TimeUnit;

public class MemoryInput {
  public static void main(String[] args)
          throws IOException, InterruptedException {
    StringReader in = new StringReader(BufferedInputFile.read("/home/miles/learning/java/thinking_in_java/src/main/java/io/MemoryInput.java"));
    int c;
    while((c = in.read()) != -1){
      System.out.print((char)c);
      TimeUnit.MILLISECONDS.sleep(100);
    }
  }
} /* (Execute to see output) *///:~
