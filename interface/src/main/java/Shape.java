public interface Shape {
  static final String UNIT = "m²";

  double area();

  default String type() {
    return this.getClass().getName();
  }

  static String print(Shape shape) {
    return "The " + shape.type() + " has an area of " + shape.area() + UNIT;
  }
}
