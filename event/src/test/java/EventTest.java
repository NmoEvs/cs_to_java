import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EventTest {
  @Test
  public void itIsImplementedWithTheObserverPattern() {
    AService service = new AService();
    StringLogger myLogger = new StringLogger();
    service.addLogger(myLogger);

    assertEquals(0, myLogger.getLogs().size());

    service.call();
    assertEquals("AService did something.", myLogger.getLogs().get(0));
  }
}
