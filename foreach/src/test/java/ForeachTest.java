import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ForeachTest {
  @Test
  public void itIteratesThroughACollection() {
    int[] myInts = {1, 2, 3, 4, 5};
    int sum = Main.sumOverInts(myInts);
    assertEquals(15, sum);
  }

  @Test
  public void howToIterate() {
    List<String> myList = new ArrayList<>();
    myList.add("One");
    myList.add("Two");
    myList.add("Three");

    System.out.println("Simple for loop");
    for (int i = 0; i < myList.size(); i++) {
      System.out.println(myList.get(i));
    }

    System.out.println("For each loop");
    for (String s : myList) {
      System.out.println(s);
    }

    System.out.println("Iteration");
    Iterator<String> e = myList.iterator();
    while (e.hasNext()) {
      System.out.println(e.next());
    }

    System.out.println("foreach() function");
    myList.forEach(s -> System.out.println(s));
    myList.forEach(System.out::println);

    System.out.println("foreach() on a Stream");
    myList.stream().forEach(s -> System.out.println(s + " with thread " + Thread.currentThread().toString()));
    myList.stream().forEach(System.out::println);

    System.out.println("foreach() using parallel threads on Streams.");
    myList.parallelStream().forEach(s -> System.out.println(s + " with thread " + Thread.currentThread().toString()));
    myList.parallelStream().forEach(System.out::println);

    assert true;

  }
}
