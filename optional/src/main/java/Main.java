import java.util.Optional;

public class Main {
  public static void main(String[] args){

      }

  public static String getNullable(String s){
    return s;
  }

  public static Optional<String> getOptional(String s){
    return Optional.ofNullable(s);
  }

}
