import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringTest {
  @Test
  public void itDoenstHaveAnEqualityOperator() {
    String str1 = new String("the same");
    String str2 = new String("the same");
    assertFalse(Main.compareObjectReferences(str1, str2));
  }

  @Test
  public void literalRefsSameObject() {
    String str1 = "the same";
    String str2 = "the same";
    assertTrue(Main.compareObjectReferences(str1, str2));
  }

  @Test
  public void itCanCompareTwoStrings() {
    String str1 = "the same";
    String str2 = "the same";
    assertTrue(Main.compareStrings(str1, str2));
  }

  @Test
  public void itIsImmutable() {
    String str1 = "the same";
    Main.operatesOnString(str1);
    assertEquals("the same", str1);
  }

  @Test
  public void itCanConcatenate() {
    String str1 = "the ";
    String str2 = "same";
    assertEquals("the same", Main.concatenate(str1, str2));
  }

  @Test
  public void itUsesExclusiveUpperBoundWhenGettingSub() {
    String str = "the same";
    assertEquals("the", Main.substring(str, 0, 3));
  }
}
