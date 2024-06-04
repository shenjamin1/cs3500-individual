/**
 * Test for knight implementation.
 */
public class KnightTest extends AbstractChessPieceTest {
  @Override
  protected ChessPiece createChessPiece(int row, int col, Color color) {
    return new Knight(row, col, color);
  }

  @Override
  protected void setupResults(int row, int col) {
    for (int i = -2; i < 3; i++) {
      for (int j = -2; j < 3; j++) {
        if (Math.abs(i) + Math.abs(j) == 3) {
          int rowResult = row + i;
          int colResult = col + j;
          if (rowResult >= 0 && colResult >= 0 && rowResult < 8 && colResult < 8) {
            results[rowResult][colResult] = true;
          }
        }
      }
    }
  }
}
