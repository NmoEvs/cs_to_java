import java.util.HashSet;
import java.util.Set;

public class Main {
  public static void main(String[] args){
    System.out.println("Run the test with `gradle check`!");
  }

  public static Set<Integer> createASetFromAnArray(int[] myInts) {
    Set<Integer> set = new HashSet<Integer>();
    for( int idx = 0 ; idx < myInts.length ; idx++ ) {
      set.add(myInts[idx]);
    }
    return set;
  }

}
