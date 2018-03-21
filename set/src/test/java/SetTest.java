import org.junit.Test;

import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class SetTest {
  @Test
  public void itCanOnlyHaveInstancesOnce() {
    int[] myInts = {1, 2, 3, 2};
    Set<Integer> set = Main.createASetFromAnArray(myInts);

    assertEquals(set.size(), 3);
  }


}
