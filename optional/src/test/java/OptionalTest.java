import org.junit.Test;

import java.util.Optional;

public class OptionalTest {

  @Test
  public void classicNullableObject() {

    String s = Main.getNullable("test");
    if (s != null){
      System.out.println(s);
    }
    assert s != null;

    s = Main.getNullable(null);
    if (s != null){
      System.out.println(s);
    }
    assert s == null;

  }

  @Test
  public void optional() {

    Optional<String> s = Main.getOptional("test");
    s.ifPresent(System.out::println);
    assert s.isPresent();

    s = Main.getOptional(null);
    s.ifPresent(System.out::println);
    assert !s.isPresent();

  }

}