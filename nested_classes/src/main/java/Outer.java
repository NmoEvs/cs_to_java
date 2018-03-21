public class Outer {
  private static final String HELLO = "Hello";
  private String name = "Outer";

  public Outer(String name) {
    this.name = name;
  }

  public static class Nested {
    public String hello() {
      return HELLO;
    }
  }

  public class Inner {
    public String getOuterName() {
      return Outer.this.name;
    }
  }

  public Inner createInner() {
    return new Inner();
  }

  public Nested createNestedChild(String name) {
    return new Nested() {
      public String hello() {
        return "Hello " + name;
      }
    };
  }

}
