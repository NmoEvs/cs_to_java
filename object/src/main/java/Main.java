public class Main {
  public static void main(String[] args) {
    System.out.println("Run the test with `gradle check`!");
  }

  public static boolean isAnObject(String str) {
    return str instanceof java.lang.Object;
  }

  public static String getClassName(Object obj) {
    return obj.getClass().getName();
  }
}
