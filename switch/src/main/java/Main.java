public class Main {
  public static void main(String[] args) {
    System.out.println("Run the test with `gradle check`!");
  }

  public static boolean isItJanuary(String str) {
    switch (str) {
      case "January": return true;
      case "December":
      case "November":
      case "Octoboer":
      case "September":
      case "August":
      case "July":
      case "June":
      case "May":
      case "April":
      case "March":
      case "February": return false;
    }
    return false;
  }

  public static boolean isItTenOrTwenty(Integer num) {
    switch (num) {
      case 10:
      case 20: return true;
    }
    return false;
  }

  public static int itCountsMonthBefore(String month) {
    int count = 0;
    switch (month) {
      case "December":
      case "November":  count++;
      case "Octoboer":  count++;
      case "September": count++;
      case "August":    count++;
      case "July":      count++;
      case "June":      count++;
      case "May":       count++;
      case "April":     count++;
      case "March":     count++;
      case "February":  count++;
      case "January":   count++;
    }
    return count;
  }
}
