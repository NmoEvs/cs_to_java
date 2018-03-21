public class Circle {
  private static double pi = 3.14;
  public static final double RADIAN;
  static {
    RADIAN = 180.0 / pi;
  }

  private double radius;

  public Circle(double radius) {
    this.radius = radius;
  }

  public void changeCommonPiValue(double new_pi) {
    pi = new_pi;
  }
  public double getPiValue() {
    return pi;
  }
}
