import java.util.List;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ListTest {
  @Test
  public void itCanOnlyListObject() {
    int[] myInts = {1, 2, 3};
    List<Integer> list = Main.createAListFromAnArray(myInts);

    assertEquals(Arrays.asList(1, 2, 3),  list);
  }

  @Test
  public void itCanReturnAnElementAtAnIndex() {
    List<String> list = Arrays.asList("one", "two", "three");

    assertEquals("two", Main.retrieveFrom(list, 1));
  }
}
