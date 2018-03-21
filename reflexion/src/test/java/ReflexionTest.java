import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReflexionTest {

  @Test
  public void findAMethod() {
    try {
      Class<?> c = Class.forName("Main");
      List<Method> allMethods = Arrays.asList(c.getDeclaredMethods());
      Optional<Method> getAStringMethod = allMethods.stream().filter(method -> "getAString".equals(method.getName())).findFirst();
      assert getAStringMethod.isPresent();
      assert getAStringMethod.get().getModifiers() == Modifier.PUBLIC + Modifier.STATIC;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void openAPrivateMethod() {
    try {
      Class<?> c = Class.forName("Main");
      List<Method> allMethods = Arrays.asList(c.getDeclaredMethods());
      Optional<Method> aPrivateMethod = allMethods.stream().filter(method -> "aPrivateMethod".equals(method.getName())).findFirst();
      assert aPrivateMethod.isPresent();
      assert aPrivateMethod.get().getModifiers() == Modifier.PRIVATE + Modifier.STATIC;
      aPrivateMethod.get().setAccessible(true);
      String s = (String) aPrivateMethod.get().invoke(null);
      assert "I'm private".equals(s);
    } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }
}
