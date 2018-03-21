import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.Arrays;

public class StreamTest {
  @Test
  public void itCanFilter() {
    List<String> names = Arrays.asList("Christophe", "Benoit", "Stephane", "Ben", "Benjamin");
    List<String> filteredNames = Main.filter(names);

    assertEquals(3, filteredNames.size());
    assertEquals("Benoit", filteredNames.get(0));
    assertEquals("Benjamin", filteredNames.get(2));
  }
}
