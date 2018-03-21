import java.util.ArrayList;
import java.nio.file.*;
import java.io.*;

public class Main {
  public static void main(String[] args){
    System.out.println("Run the test with `gradle check`!");
  }

  public static Path createAFile() throws IOException {
    return Files.createTempFile("tmp_test_create_a_file", ".txt");
  }

  public static void deleteAFile(Path file) throws IOException {
    Files.delete(file);
  }

  public static ArrayList<String> readAllLinesFrom(Path file) {
    ArrayList<String> lines = new ArrayList<String>();
    try (BufferedReader reader = Files.newBufferedReader(file)) {
      String line = null;
      while ((line = reader.readLine()) != null) {
          lines.add(line);
      }
    } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
    }
    return lines;
  }

  public static Path writeAllLinesIntoAFile(String[] lines) {
    Path file = null;
    try {
      file = Files.createTempFile("tmp_test_write_in_a_file", ".txt");
    } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
      return file;
    }

    try (BufferedWriter writer = Files.newBufferedWriter(file)) {
      for(String line: lines) {
        writer.write(line);
        writer.newLine();
      }
    } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
    }
    return file;
  }

}
