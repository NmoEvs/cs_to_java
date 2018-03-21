import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// This is mainly inspired from JavaTests
// https://github.com/matyb/java-koans/

public class StreamTest {
  String str = "";

  List<String> places = Arrays.asList("Bruxelles", "Liege", "Mons", "Gand", "Bruges", "Namur");

  @Test
  public void simpleCount() {
    long count = places.stream().count();
    assertEquals(count, 6);
    // assertEquals(count, null);
  }

  @Test
  public void filteredCount() {
    long count = places.stream()
                       .filter(s -> s.startsWith("B"))
                       .count();
    assertEquals(count, 2);
    // assertEquals(count, null);
  }

  @Test
  public void max() {
    String longest = places.stream()
                           .max(Comparator.comparing(cityName -> cityName.length()))
                           .get();
    assertEquals(longest, "Bruxelles");
    // assertEquals(longest, null);
  }

  @Test
  public void min() {
    String shortest = places.stream()
                            .min(Comparator.comparing(cityName -> cityName.length()))
                            .get();
    assertEquals(shortest, "Mons");
  }

  @Test
  public void reduce() {
    String join = places.stream()
                        .reduce("", String::concat);
    assertEquals(join, "BruxellesLiegeMonsGandBrugesNamur");
    // assertEquals(join, null);
  }

  @Test
  public void reduceWithoutStarterReturnsOptional() {
    Optional<String> join = places.stream()
                                  .reduce(String::concat);
    assertEquals(join.get(), "BruxellesLiegeMonsGandBrugesNamur");
    // assertEquals(join.get(), null);
  }

  @Test
  public void join() {
    String join = places.stream()
                        .reduce((accumulated, cityName) -> accumulated + "\", \"" + cityName)
                        .get();
    assertEquals(join, "Bruxelles\", \"Liege\", \"Mons\", \"Gand\", \"Bruges\", \"Namur");
    // assertEquals(join, null);
  }

  @Test
  public void stringJoin() {
    String join = places.stream()
                        .collect(Collectors.joining("\", \""));
    assertEquals(join, "Bruxelles\", \"Liege\", \"Mons\", \"Gand\", \"Bruges\", \"Namur");
    // assertEquals(join, null);
  }

  @Test
  public void mapReduce() {
    OptionalDouble averageLengthOptional = places.stream()
                                                 .mapToInt(String::length)
                                                 .average();
    double averageLength = Math.round(averageLengthOptional.getAsDouble());
    assertEquals(averageLength, 6.0, 0.0);
    // assertEquals(averageLength, null);
  }

  @Test
  public void parallelMapReduce() {
      int lengthSum = places.parallelStream()
                            .mapToInt(String::length)
                            .sum();
      assertEquals(lengthSum, 33);
      // assertEquals(lengthSum, null);
  }

  @Test
  public void limitSkip() {
    int lengthSum_Limit_3_Skip_1 = places.stream()
                                         .mapToInt(String::length)
                                         .limit(3)
                                         .skip(1)
                                         .sum();
    assertEquals(lengthSum_Limit_3_Skip_1, 9);
    // assertEquals(lengthSum_Limit_3_Skip_1, null);
  }

  @Test
  public void lazyEvaluation() {
    Stream stream = places.stream()
                          .filter(s -> {
                              str = "hello";
                              return s.startsWith("B");
                          });
    assertEquals(str,"");
    // assertEquals(str, null);
  }

  @Test
  public void sumRange() {
    int sum = IntStream.range(1, 4).sum();
    assertEquals(sum, 6);
    // assertEquals(sum, null);
  }

  @Test
  public void rangeToList() {
    List<Integer> range = IntStream.range(1, 4)
                                   .boxed()
                                   .collect(Collectors.toList());
    assertEquals(range, Arrays.asList(1, 2, 3));
    // assertEquals(range, null);
  }
}
