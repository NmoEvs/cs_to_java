public class Main {
  public static void main(String[] args){
    System.out.println("Run the test with `gradle check`!");
  }

  public static boolean compareObjectReferences(Object obj1, Object obj2) {
    return obj1 == obj2;
  }

  public static boolean compareStrings(String str1, String str2) {
    return str1.equals(str2);
  }

  public static void operatesOnString(String str) {
    str.toLowerCase();
    str.concat("another one");
  }

  public static String concatenate(String str1, String str2) {
    return str1 + str2;
  }

  public static String substring(String str, int begin, int end) {
    return str.substring(begin, end);
  }
}
