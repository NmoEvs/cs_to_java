import java.util.ArrayList;

public class Main {
  public static void main(String[] args){
    System.out.println("Run the test with `gradle check`!");
  }

  public static boolean compareRunTimeClass(ArrayList<?> list1, ArrayList<?> list2) {
    return list1.getClass() == list2.getClass();
  }

  public static ArrayList<Object> copyFrom(ArrayList<?> fromList) {
    ArrayList<Object> toList = new ArrayList<Object>();
    for(int idx = 0 ; idx < fromList.size() ; idx++) {
      toList.add(fromList.get(0));
    }
    return toList;
  }

  public static String toText(ArrayList<?> list) {
    String str = new String();
    for(Object obj: list) {
      str += obj;
    }
    return str;
  }

  public static <T> ArrayList<T> fromArrayToList(T[] array) {
      ArrayList<T> list = new ArrayList();
      for (T obj: array) {
          list.add(obj);
      }
      return list;
  }

  public static int getFirstNumberAsInt(ArrayList<? extends Number> listOfNumber) {
    return listOfNumber.get(0).intValue();
  }
}
