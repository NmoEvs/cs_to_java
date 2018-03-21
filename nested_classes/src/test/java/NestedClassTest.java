import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class NestedClassTest {
  @Test
  public void itCanBeStatic() {
    Outer.Nested nested = new Outer.Nested();
    assertEquals("Hello", nested.hello());
  }

  @Test
  public void itCanBeInner() {
    Outer outer = new Outer("GoodBye");
    Outer.Inner inner = outer.createInner();
    assertEquals("GoodBye", inner.getOuterName());
  }

  @Test
  public void itCanBeAnonymous() {
    Outer outer = new Outer("GoodBye");
    Outer.Nested nestedChild = outer.createNestedChild("Christophe");
    assertEquals("Hello Christophe", nestedChild.hello());
  }
}
