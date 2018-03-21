import org.junit.Test;
import static org.junit.Assert.assertEquals;
import mypackage.OneClass;

public class PackageTest {
  @Test
  public void itsClassCanBeImported() {
    assertEquals(true, OneClass.ISACLASS);
  }

  @Test
  public void itsClassIsInternalByDefault() {
    assertEquals(true, OneClass.accessInternalClassConstant());
  }
}
