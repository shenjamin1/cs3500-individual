/**
 * Knight implementation of a chess piece.
 */
public class Knight extends AbstractChessPiece {
  /**
   * Constructor for knight implementation.
   *
   * @param row   row the knight was initialized in
   * @param col   column the knight was initialized in
   * @param color team color of the knight
   * @throws IllegalArgumentException if the row or column is negative
   */
  public Knight(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Can this chess piece be moved from its current location to the location
   * (row,col).
   *
   * @param row the row to which this knight can be moved
   * @param col the col to which this knight can be moved
   * @return true if it can be moved to this position, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    return super.canMove(row, col)
            && ((Math.abs(this.row - row) == 2 && Math.abs(this.col - col) == 1)
            || (Math.abs(this.row - row) == 1 && Math.abs(this.col - col) == 2));
  }
}
