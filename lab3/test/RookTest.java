/**
 * Test for rook implementation.
 */
public class RookTest extends AbstractChessPieceTest {

  @Override
  protected ChessPiece createChessPiece(int row, int col, Color color) {
    return new Rook(row, col, color);
  }

  @Override
  protected void setupResults(int row, int col) {
    //check if canMove works
    for (int i = 0; i < 8; i++) {
      results[i][col] = true;
      results[row][i] = true;
    }
  }
}