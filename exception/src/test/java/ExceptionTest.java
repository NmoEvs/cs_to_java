import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ExceptionTest {
  @Test
  public void itChecksException() {
    try {
      Main.throwException();
    } catch (Exception e) {
      assertEquals("This is a checked Excpetion!", e.getMessage());
    }
  }

  @Test
  public void itDontHaveToCheckRuntimeException() {
    try {
      Main.throwRuntimeException();
    } catch (RuntimeException e) {
      assertEquals("This is an unchecked Exception!", e.getMessage());
    }
  }

  @Test
  public void itCanBeChained() {
    try {
      Main.throwChainedExceptions();
    } catch (Exception e) {
      assertEquals("Those are chained Exceptions.", e.getMessage());
      assertEquals("This is a checked Excpetion!", e.getCause().getMessage());
    }
  }

  @Test
  public void itIsDisabledByAnyJumpInFinally() {
    try {
      Main.throwExceptionDisabledByFinally();
    } catch (Exception e) {
      fail("This shouldn't be reached");
    }
  }
}
