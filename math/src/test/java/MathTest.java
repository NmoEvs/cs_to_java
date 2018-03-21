import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MathTest {
  @Test
  public void itDoesntCheckByDefaultOverflow() {
    byte nbr = 127;
    nbr += 1;
    assertEquals(-128, nbr);
  }

  @Test
  public void itHasMathMethodCheckingForOverflow() {
    int nbr = Integer.MAX_VALUE;
    try {
      nbr = Math.addExact(nbr, 1);
      fail("It should throw an Exception before.");
    } catch (ArithmeticException e) {
      assertEquals(Integer.MAX_VALUE, nbr);
    }
  }
}
