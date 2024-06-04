/**
 * Test for bishop implementation.
 */
public class BishopTest extends AbstractChessPieceTest {

  @Override
  protected ChessPiece createChessPiece(int row, int col, Color color) {
    return new Bishop(row, col, color);
  }

  @Override
  protected void setupResults(int row, int col) {
    //check if canMove works
    for (int i = 0; i < 8; i++) {

      if ((row + i) < 8) {
        if ((col + i) < 8) {
          results[row + i][col + i] = true;
        }
        if (col >= i) {
          results[row + i][col - i] = true;
        }

      }

      if (row >= i) {
        if ((col + i) < 8) {
          results[row - i][col + i] = true;
        }
        if (col >= i) {
          results[row - i][col - i] = true;
        }
      }
    }
  }
}