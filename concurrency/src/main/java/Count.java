import java.lang.Runnable;

public class Count implements Runnable {
  public int counter = 0;
  public void run() {
    for(int i = 0; i < 10 ; i++) {
      counter++;
    }
  }
}
