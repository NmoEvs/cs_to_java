import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class SwitchTest {
  @Test
  public void itCanCompareString() {
    assertTrue(Main.isItJanuary("January"));
  }

  @Test
  public void itCanCompareBoxedAndUnboxedType() {
    assertTrue(Main.isItTenOrTwenty(new Integer(10)));
  }

  @Test
  public void itAllowsFallThrough() {
    assertEquals(11, Main.itCountsMonthBefore("December"));
  }
}
