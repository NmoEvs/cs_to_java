import java.util.stream.Collectors;
import java.util.List;

public class Main {
  public static void main(String[] args){
    System.out.println("Run the test with `gradle check`!");
  }

  public static List<String> filter(List<String> names) {
    return names.stream()
                .filter(c -> c.contains("Ben"))
                .collect(Collectors.toList());
  }
}
