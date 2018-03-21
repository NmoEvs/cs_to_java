import java.util.ArrayList;
public class StringLogger implements Logger {
  private ArrayList<String> logs = new ArrayList<String>();
  public void info(String msg) {
    logs.add(msg);
  }

  public ArrayList<String> getLogs() {
    return logs;
  }
}
