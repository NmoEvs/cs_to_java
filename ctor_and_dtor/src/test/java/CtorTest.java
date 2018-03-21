import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CtorTest {
  @Test
  public void itCallsSuperImplicitly() {
    Dog aDog = new Dog();
    assertEquals("An animal", aDog.getName());
  }

  @Test
  public void  itCallsSuperExplicitly() {
    Dog aDog = new Dog("Rantanplan");
    assertEquals("Rantanplan", aDog.getName());
  }
}
