package reusing;//: reusing/Beetle.java
// The full process of initialization.
import static net.mindview.util.Print.*;

class Insect {
  private int i = 9;
  protected int j;
  Insect() {
    print("i = " + i + ", j = " + j);
    j = 39;
  }
  static{
    print("static block Insect");
  }
  private static int x1 =
    printInit("static Insect.x1 initialized");
  static int printInit(String s) {
    print(s);
    return 47;
  }
  {
    int m = printInit("block Insect");
  }

  private int m = printInit("Insect");
}

public class Beetle extends Insect {
  private int k = printInit("Beetle.k initialized");
  static{
    print("static block Beetle");
  }
  public Beetle() {
    print("k = " + k);
    print("j = " + j);
  }
  private static int x2 =
    printInit("static Beetle.x2 initialized");
  {
    int n = printInit("block Beetle");
  }

  private int n = printInit("Beetle");
  public static void main(String[] args) {
    print("Beetle constructor");
    Beetle b = new Beetle();
  }
} /* Output:
static Insect.x1 initialized
static Beetle.x2 initialized
Beetle constructor
i = 9, j = 0
Beetle.k initialized
k = 47
j = 39
*///:~
