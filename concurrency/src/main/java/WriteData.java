import java.lang.Runnable;

class WriteData implements Runnable {
  private Data data;
  private String toWrite;
  private int pauseToDo;
  public WriteData(Data data, String toWrite, int pauseToDo) {
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
