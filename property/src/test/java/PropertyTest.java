import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PropertyTest {
  @Test
  public void itCanGet() {
    Person p = new Person("Christophe");
    assertEquals("Christophe", p.getName());
  }

  @Test
  public void itCanSet() {
    Person p = new Person("Christophe");
    p.setName("Benoit");
    assertEquals("Benoit", p.getName());
  }
}
