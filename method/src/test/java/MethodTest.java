import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MethodTest {
  @Test
  public void itCanBeOverriden() {
    Animal theDog = new Dog();
    assertEquals("The dog barks.", theDog.sound());
  }

  @Test
  public void itCanBeOverloaden() {
    Dog theDog = new Dog();
    Dog anotherDog = new Dog();
    Cat aCat = new Cat();

    assertFalse(aCat.like(theDog));
    assertFalse(theDog.like(aCat));
    assertTrue(theDog.like(anotherDog));
  }

  @Test
  public void itSupportsCovariantReturnType() {
    Animal theDog = new Dog();
    Animal anotherDog = theDog.breed();
    assertEquals(theDog.sound(), anotherDog.sound());
  }

  @Test
  public void itCanBeFinal() {
    Cat aCat = new Cat();
    assertEquals("The cat", aCat.thisAnimal());
  }
}
