import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MultiDimensionalArrayTest {
  @Test
  public void itIsTrue() {
    int nbrOfRows = 4;
    int nbrOfCols = 6;
    int[][] myMatrix = Main.createMatrixOfInt(nbrOfCols, nbrOfRows, 3);
    assertEquals(nbrOfCols, myMatrix.length);
    assertEquals(nbrOfRows, myMatrix[0].length);
    assertEquals(3, myMatrix[1][2]);
  }
}
