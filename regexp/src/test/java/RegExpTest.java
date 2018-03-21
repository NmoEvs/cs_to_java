import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// This is mainly inspired from JavaTests
// https://github.com/matyb/java-koans/

public class RegExpTest {

  @Test
  public void basicMatching() {
    Pattern p = Pattern.compile("xyz");
    Matcher m = p.matcher("xyzxxxxyz");
    assertEquals(m.find(), true);
    assertEquals(m.start(), 0);
    assertEquals(m.find(), true);
    assertEquals(m.start(), 6);
    assertEquals(m.find(), false);
    // assertEquals(m.find(), null);
    // assertEquals(m.start(), null);
    // assertEquals(m.find(), null);
    // assertEquals(m.start(), null);
    // assertEquals(m.find(), null);
  }

  @Test
  public void extendedMatching() {
    Pattern p = Pattern.compile("x.z");
    Matcher m = p.matcher("xyz u x z u xfz");
    assertEquals(m.find(), true);
    assertEquals(m.start(), 0);
    assertEquals(m.find(), true);
    assertEquals(m.start(), 6);
    assertEquals(m.find(), true);
    assertEquals(m.start(), 12);
    // assertEquals(m.find(), null);
    // assertEquals(m.start(), null);
    // assertEquals(m.find(), null);
    // assertEquals(m.start(), null);
    // assertEquals(m.find(), null);
    // assertEquals(m.start(), null);
  }

  @Test
  public void escapingMetaCharacters() {
    Pattern p = Pattern.compile("end\\.");
    Matcher m = p.matcher("begin. end.");
    assertEquals(m.find(), true);
    assertEquals(m.start(), 7);
    // assertEquals(m.find(), null);
    // assertEquals(m.start(), null);
  }

  @Test
  public void splittingStrings() {
    String csvDataLine = "1,name,description";
    String[] data = csvDataLine.split(","); // you can use any regex here
    assertEquals(data[0], "1");
    assertEquals(data[1], "name");
    assertEquals(data[2], "description");
    // assertEquals(data[0], null);
    // assertEquals(data[1], null);
    // assertEquals(data[2], null);
  }
}
