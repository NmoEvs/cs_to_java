public class Main {
  public static void main(String[] args) {
    System.out.println("Run the test with `gradle check`!");
  }

  public static void throwException() throws Exception {
    throw new Exception("This is a checked Excpetion!");
  }

  public static void throwRuntimeException() {
    throw new RuntimeException("This is an unchecked Exception!");
  }

  public static void throwChainedExceptions() throws Exception {
    try {
      throwException();
    } catch (Exception e) {
      throw new Exception("Those are chained Exceptions.", e);
    }
  }

  public static void throwExceptionDisabledByFinally() throws Exception {
    try {
      throwException();
    } catch (Exception e) {
      throw new Exception("This is disabled.", e);
    } finally {
      return;
    }
  }
}
