/**
 * Bishop implementation of a chess piece.
 */
public class Bishop extends AbstractChessPiece {
  /**
   * Constructor for bishop implementation.
   *
   * @param row   row the bishop was initialized in
   * @param col   column the bishop was initialized in
   * @param color team color of the bishop
   * @throws IllegalArgumentException if the row or column is negative
   */
  public Bishop(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Can this chess piece be moved from its current location to the location
   * (row,col).
   *
   * @param row the row to which this bishop can be moved
   * @param col the col to which this bishop can be moved
   * @return true if it can be moved to this position, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    return super.canMove(row, col) && Math.abs(this.row - row) == Math.abs(this.col - col);
  }
}