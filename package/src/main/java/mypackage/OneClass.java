package mypackage;

public class OneClass {
  public static final boolean ISACLASS = true;

  public static boolean accessInternalClassConstant() {
    return InternalClass.ISINTERNAL;
  }
}
