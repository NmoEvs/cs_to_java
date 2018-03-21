import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;

public class GenericTypeTest {
  @Test
  public void allItsInstancesAreOfTheSameRunTimeClass() {
    ArrayList<Integer> listOfIntegers = new ArrayList<Integer>();
    ArrayList<String> listOfStrings = new ArrayList<String>();

    assertTrue(Main.compareRunTimeClass(listOfIntegers, listOfStrings));
  }

  @Test
  public void itIsNotTransitiveForInheritance() {
    ArrayList<Integer> listOfIntegers = new ArrayList<Integer>();
    listOfIntegers.add(1);
    listOfIntegers.add(2);

    ArrayList<Object> listOfObjects = Main.copyFrom(listOfIntegers);
    assertEquals(1, listOfObjects.get(0));
  }

  @Test
  public void itCanHaveWildcardType() {
    ArrayList<String> listOfStrings = new ArrayList<String>();
    listOfStrings.add("Hello");
    listOfStrings.add(" ");

    ArrayList<Integer> listOfIntegers = new ArrayList<Integer>();
    listOfIntegers.add(1);
    listOfIntegers.add(2);
    listOfIntegers.add(3);

    String text = Main.toText(listOfStrings);
    text += Main.toText(listOfIntegers);

    assertEquals("Hello 123", text);
  }

  @Test
  public void itCanDefineGenericMethod() {
    Integer[] myInts = {1, 2, 3};
    ArrayList<Integer> listOfIntegers = Main.fromArrayToList(myInts);

    assertEquals(new Integer(2), listOfIntegers.get(1));
  }

  @Test
  public void itCanBeConstrained() {
    ArrayList<Integer> listOfIntegers  = new ArrayList<Integer>();
    listOfIntegers.add(1);
    listOfIntegers.add(2);
    listOfIntegers.add(3);

    assertEquals(1, Main.getFirstNumberAsInt(listOfIntegers));
  }
}

// * In Java, the derivation constraint is the only constraint that could be
//   applied on a generic type.
