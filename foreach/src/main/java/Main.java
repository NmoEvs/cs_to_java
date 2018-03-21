public class Main {
  public static void main(String[] args){
    System.out.println("Run the test with `gradle check`!");
  }

  public static int sumOverInts(int[] array) {
    int sum = 0;
    for(int elem: array) {
      sum += elem;
    }
    return sum;
  }
}
