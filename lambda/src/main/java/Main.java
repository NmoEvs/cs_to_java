public class Main {
  public static void main(String[] args){
    System.out.println("Run the test with `gradle check`!");
  }

  public static String apply(String name, Greeting op) {
    return op.greet(name);
  }
}
