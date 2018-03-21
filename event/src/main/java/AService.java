import java.util.ArrayList;

public class AService {
  private ArrayList<Logger> loggers = new ArrayList<Logger>();
  public void addLogger(Logger logger) {
    loggers.add(logger);
  }

  public void call() {
    // do something first
    // then call the listeners
    for(Logger logger: loggers) {
      logger.info("AService did something.");
    }
  }
}
