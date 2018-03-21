public class Main {
  public static void main(String[] args){
    System.out.println("Run the test with `gradle check`!");
  }

  public static boolean isFive(int nbr) {
    return nbr == 5;
  }

  public static boolean isFive(double nbr) {
    return nbr == 5.0;
  }

  public static boolean isFive(char c) {
    return c == '5';
  }

  public static int sumInts(int[] myInts) {
    int sum = 0;
    for(int idx = 0 ; idx < myInts.length ; idx++) {
      sum += myInts[idx];
    }
    return sum;
  }

  public static boolean compareWithBoxedVersion(int actual, Integer expected) {
    return expected.equals(actual);
  }

  public static Integer autoBoxIt(Integer i) {
    return i;
  }
}
