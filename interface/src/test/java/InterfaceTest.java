import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class InterfaceTest {
  @Test
  public void itCanBeImplemented() {
    Shape shape = new Square(1.0);
    assertEquals(1.0, shape.area(), 0.0);
  }

  @Test
  public void itCanHaveConstant() {
    assertEquals("m²", Shape.UNIT);
  }

  @Test
  public void itCanProvideDefaultImplementation() {
    Shape shape = new Square(1.0);
    assertEquals("Square", shape.type());
  }

  @Test
  public void itCanHaveStaticMethod() {
    assertEquals("The Square has an area of 1.0m²", Shape.print(new Square(1.0)));
  }
}
