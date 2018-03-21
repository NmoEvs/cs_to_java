public class Data {
  private String aData;

  public Data(String aData) {
    this.aData = aData;
  }

  public synchronized void write(String newData) {
    aData = newData;
  }

  public synchronized String read() {
    return aData;
  }
}
