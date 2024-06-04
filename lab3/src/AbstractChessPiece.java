/**
 * This is an abstract class that combines the
 * repetitive code in the piece subclasses.
 */
abstract class AbstractChessPiece implements ChessPiece {
  protected int row;
  protected int col;
  protected Color color;

  /**
   * Constructor for an abstract chess piece.w
   *
   * @param row   row the abstract chess piece was initialized in
   * @param col   column the abstract chess piece was initialized in
   * @param color team color of the abstract chess piece
   * @throws IllegalArgumentException if the row or column is negative
   */
  protected AbstractChessPiece(int row, int col, Color color) throws IllegalArgumentException {
    if (row < 0 || col < 0 || row >= 8 || col >= 8) {
      throw new IllegalArgumentException("Illegal position");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  /**
   * Can the abstract chess piece be moved from its current location to the location
   * (row,col).
   *
   * @param row the row to which this abstract chess piece can be moved
   * @param col the col to which this abstract chess piece can be moved
   * @return true if it can be moved to this position, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    return (row >= 0) && (col >= 0) && (row < 8) && (col < 8);
  }

  /**
   * Can this abstract chess piece kill the chess piece passed to this method?.
   *
   * @param piece the piece that may or may not be killed by this piece
   * @return true if this piece can kill the other, false otherwise
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    return (this.getColor() != piece.getColor()) && canMove(piece.getRow(), piece.getColumn());
  }

  /**
   * Get the row of the current position of this piece. Rows begin with 0.
   *
   * @return the row of the current position of this piece
   */
  @Override
  public int getRow() {
    return row;
  }

  /**
   * Get the column of the current position of this piece Columns begin with 0.
   *
   * @return the column of the current position of this piece
   */
  @Override
  public int getColumn() {
    return col;
  }

  /**
   * Get the color of this piece. The color can be one of WHITE or BLACK.
   *
   * @return the color of this chess piece
   */
  @Override
  public Color getColor() {
    return color;
  }
}
