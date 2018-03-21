public class Main {
  public static void main(String[] args){
    System.out.println("Run the test with `gradle check`!");
  }

  public static int[][] createMatrixOfInt(int nbrOfCols, int nbrOfRows, int default_value) {
    int[][] matrix = new int[nbrOfCols][nbrOfRows];
    for(int col = 0 ; col < matrix.length ; col++) {
      for(int row = 0 ; row < matrix[col].length ; row++) {
        matrix[col][row] = default_value;
      }
    }
    return matrix;
  }
}
