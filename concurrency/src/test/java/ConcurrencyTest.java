import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.Thread;
import java.lang.InterruptedException;
import java.util.concurrent.*;

public class ConcurrencyTest {
  @Test
  public void itCanRunAndWaitThread() {
    Count count = new Count();
    Thread countThread = new Thread(count);
    countThread.start();
    try {
      countThread.join();
      assertEquals(count.counter, 10);
      // assertEquals(count.counter, null);
    } catch (InterruptedException ie) {
      fail("it shouldn't be interrupted.");
    }
  }

  @Test
  public void itCanSynchronizeMethod() {
    Data data = new Data("Hello");
    WriteData writer1 = new WriteData(data, "World", 10);
    WriteData writer2 = new WriteData(data, "!", 20);
    Thread writer1Thread = new Thread(writer1);
    Thread writer2Thread = new Thread(writer2);
    writer2Thread.start();
    writer1Thread.start();
    try {
      Thread.sleep(30);
      assertEquals("!", data.read());
      // assertEquals(null, data.read());
    } catch (InterruptedException ie) {
      fail("it shouldn't be interrupted");
    }
  }

  @Test
  public void itProvideAtomicsAndExecutor() {
    AtomicData data = new AtomicData(0);
    ExecutorService es = Executors.newCachedThreadPool();
    for(int i = 10 ; i > 0 ; i--) {
      es.execute(new AtomicWriteData(data, 10 * i, 10 * i));
    }
    es.shutdown();
    try {
      boolean finished = es.awaitTermination(1, TimeUnit.SECONDS);
      assertEquals(100, data.read());
      // assertEquals(null, data.read());
    } catch (InterruptedException ie) {
      fail("it shouldn't be interrupted");
    }
  }
}
