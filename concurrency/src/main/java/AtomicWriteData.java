import java.lang.Runnable;

class AtomicWriteData implements Runnable {
  private AtomicData data;
  private int toWrite;
  private int pauseToDo;
  public AtomicWriteData(AtomicData data, int toWrite, int pauseToDo) {
    this.data = data;
    this.pauseToDo = pauseToDo;
    this.toWrite = toWrite;
  }

  public void run() {
    try {
      Thread.sleep(pauseToDo);
      data.write(this.toWrite);
    } catch (InterruptedException ie) {
    }
  }
}
