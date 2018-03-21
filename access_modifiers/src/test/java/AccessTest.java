import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AccessTest {
  @Test
  public void itCanAccessToAProtectedMemberOfAClassInTheSamePackage() {
    assertEquals(true, Main.PROTECTED);
  }
}
