import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ObjectTest {
  @Test
  public void everyClassesAreChildrenOfObject() {
    String str = "This is an Object.";
    assertTrue(Main.isAnObject(str));
  }

  @Test
  public void itHasAnObjectClass() {
    String str = "Its class is String.";
    assertEquals("java.lang.String", Main.getClassName(str));
  }
}
