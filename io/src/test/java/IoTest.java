import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.nio.file.*;
import java.io.*;

public class IoTest {
  @Test
  public void fileCanBeCreatedAndDeleted() {
    try {
      Path file = Main.createAFile();
      assertEquals(Files.exists(file), true);
      Main.deleteAFile(file);
      assertEquals(Files.exists(file), false);
    } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
    }
  }

  @Test
  public void fileCanBeRead() {
    Path file = Paths.get(this.getClass().getClassLoader().getResource("a_file.txt").getPath());
    ArrayList<String> lines = Main.readAllLinesFrom(file);
    assertEquals("line 2", lines.get(1));
  }

  @Test
  public void fileCanBeWritten() {
    String[] lines = {"Hello", "World"};
    Path file = Main.writeAllLinesIntoAFile(lines);
    if (file == null) fail("The path shouldn't be null");
    ArrayList<String> readLines = Main.readAllLinesFrom(file);
    assertEquals("Hello", readLines.get(0));
    try {
      Main.deleteAFile(file);
    } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
    }
  }
}
