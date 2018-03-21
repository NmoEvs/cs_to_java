import java.util.concurrent.atomic.AtomicInteger;

public class AtomicData {
  private AtomicInteger aData = new AtomicInteger();

  public AtomicData(int aData) {
    this.aData.set(aData);
  }

  public void write(int newData) {
    aData.set(newData);
  }

  public int read() {
    return aData.get();
  }
}
