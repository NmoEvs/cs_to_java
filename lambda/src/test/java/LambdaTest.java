import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LambdaTest {
  @Test
  public void itCanBeALambda() {
    assertEquals("Hello Christophe!", Main.apply("Christophe", (name) -> "Hello " + name + "!"));
  }

  @Test
  public void itCanBeAMethodReference() {
    Bonjour bjr = new Bonjour();
    assertEquals("Bonjour Christophe !", Main.apply("Christophe", bjr::greet));
  }
}
