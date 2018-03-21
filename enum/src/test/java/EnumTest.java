import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnumTest {
  @Test
  public void itIsTypeSafe() {
    Day today = Day.MONDAY;
    assertEquals(0, today.toInt());
  }

  @Test
  public void itIsAnObject() {
    Day today = Day.FRIDAY;
    assertEquals("The best day of the week.", today.toString());
  }
}
