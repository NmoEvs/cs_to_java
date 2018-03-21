import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class PrimitiveTest {
  @Test
  public void overloadingAllowsToHandleDifferentPrimitives() {
    assertTrue(Main.isFive(5));
    assertTrue(Main.isFive('5'));
    assertTrue(Main.isFive(5.0));
  }

  @Test
  public void itCanBePutInArray() {
    int[] arrayOfInts = { 0, 1, 2, 3, 4, 5 };
    assertEquals(15, Main.sumInts(arrayOfInts));
  }

  @Test
  public void itHasBoxedVersion() {
    Integer expected = 5;
    assertTrue(Main.compareWithBoxedVersion(5, expected));
  }

  @Test
  public void itCanBeAuboxed() {
    assertEquals("java.lang.Integer", Main.autoBoxIt(5).getClass().getName());
  }

  @Test
  public void newTypeHasToBeObject() {
    Point p = Point.zero;
    assertEquals(0, p.x);
  }
}
