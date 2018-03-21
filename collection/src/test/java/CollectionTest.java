import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.*;

// This is mainly inspired from JavaKoans
// https://github.com/matyb/java-koans/

public class CollectionTest {
  @Test
  public void usingAnArrayList() {
    List<String> list = new ArrayList<String>();
    list.add("Christophe");
    list.add("Benoit");
    list.add("Stéphane");
    // assertEquals(list.get(1), null);
    assertEquals(list.get(1), "Benoit");
  }

  @Test
  public void usingAQueue() {
    Queue<String> queue = new PriorityQueue<String>();
    queue.add("1");
    queue.add("2");
    assertEquals(queue.peek(), "1");
    assertEquals(queue.size(), 2);
    assertEquals(queue.poll(), "1");
    assertEquals(queue.size(), 1);
    assertEquals(queue.poll(), "2");
    assertEquals(queue.isEmpty(), true);
    // assertEquals(queue.peek(), null);
    // assertEquals(queue.size(), null);
    // assertEquals(queue.poll(), null);
    // assertEquals(queue.size(), null);
    // assertEquals(queue.poll(), null);
    // assertEquals(queue.isEmpty(), null);
  }

  @Test
  public void usingABasicSet() {
    Set<String> set = new HashSet<String>();
    set.add("1");
    set.add("2");
    set.add("3");
    set.add("2");
    assertEquals(set.size(), 3);
    assertEquals(set.contains("2"), true);
    assertEquals(set.contains("4"), false);
    // assertEquals(set.size(), null);
    // assertEquals(set.contains("2"), null);
    // assertEquals(set.contains("4"), null);
  }

  @Test
  public void usingABasicMap() {
    Map<String, String> map = new HashMap<String, String>();
    map.put("a", "1");
    map.put("b", "2");
    assertEquals(map.size(), 2);
    assertEquals(map.containsKey("a"), true);
    assertEquals(map.containsKey("1"), false);
    assertEquals(map.containsValue("2"), true);
    assertEquals(map.get("a"), "1");
    // assertEquals(map.size(), null);
    // assertEquals(map.containsKey("a"), null);
    // assertEquals(map.containsKey("1"), null);
    // assertEquals(map.containsValue("2"), null);
    // assertEquals(map.get("a"), null);
  }

  @Test
  public void usingBackedArrayList() {
    String[] array = {"a", "b", "c"};
    List<String> list = Arrays.asList(array);
    list.set(0, "x");
    assertEquals(array[0], "x");
    // assertEquals(array[0], null);
    array[0] = "a";
    assertEquals(list.get(0), "a");
    // assertEquals(list.get(0), null);
  }

  @Test
  public void usingBackedSubMap() {
    TreeMap<String, String> map = new TreeMap<String, String>();
    map.put("a", "Aha");
    map.put("b", "Boo");
    map.put("c", "Coon");
    map.put("e", "Emu");
    map.put("f", "Fox");
    SortedMap<String, String> backedMap = map.subMap("c", "f");
    assertEquals(backedMap.size(), 2);
    assertEquals(map.size(), 5);
    // assertEquals(backedMap.size(), null);
    // assertEquals(map.size(), null);
    backedMap.put("d", "Dog");
    assertEquals(backedMap.size(), 3);
    assertEquals(map.size(), 6);
    assertEquals(map.containsKey("d"), true);
    // assertEquals(backedMap.size(), null);
    // assertEquals(map.size(), null);
    // assertEquals(map.containsKey("d"), null);
  }

  @Test
  public void differenceBetweenOrderedAndSorted() {
    TreeSet<String> sorted = new TreeSet<String>();
    sorted.add("c");
    sorted.add("z");
    sorted.add("a");
    assertEquals(sorted.first(), "a");
    assertEquals(sorted.last(), "z");
    // assertEquals(sorted.first(), null);
    // assertEquals(sorted.last(), null);

    LinkedHashSet<String> ordered = new LinkedHashSet<String>();
    ordered.add("c");
    ordered.add("z");
    ordered.add("a");
    StringBuffer sb = new StringBuffer();
    for (String s: ordered) {
      sb.append(s);
    }
    assertEquals(sb.toString(), "cza");
    // assertEquals(sb.toString(), null);
  }
}
