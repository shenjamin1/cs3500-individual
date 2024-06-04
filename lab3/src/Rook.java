/**
 * Rook implementation of a chess piece.
 */
public class Rook extends AbstractChessPiece {
  /**
   * Constructor for rook implementation.
   *
   * @param row   row the rook was initialized in
   * @param col   column the rook was initialized in
   * @param color team color of the rook
   * @throws IllegalArgumentException if the row or column is negative
   */
  public Rook(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Can this rook be moved from its current location to the location
   * (row,col).
   *
   * @param row the row to which this rook can be moved
   * @param col the col to which this rook can be moved
   * @return true if it can be moved to this position, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    return super.canMove(row, col) && ((this.row == row) || (this.col == col));
  }
}
