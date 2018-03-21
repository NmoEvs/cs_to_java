import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StaticFieldTest {
  @Test
  public void itIsSharedWithAllInstances() {
    Circle c1 = new Circle(1.0);
    Circle c2 = new Circle(2.0);
    c1.changeCommonPiValue(3.0);
    assertEquals(3.0, c2.getPiValue(), 0.0);
  }

  @Test
  public void itCanHaveConstant() {
    assertEquals(180.0/3.14, Circle.RADIAN, 0.0);
  }
}
