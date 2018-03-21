import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args){
    System.out.println("Run the test with `gradle check`!");
  }

  public static List<Integer> createAListFromAnArray(int[] myInts) {
    List<Integer> list = new ArrayList<Integer>();
    for( int idx = 0 ; idx < myInts.length ; idx++ ) {
      list.add(myInts[idx]);
    }
    return list;
  }

  public static String retrieveFrom(List<String> list, int idx) {
    return list.get(idx);
  }
}
