import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Abstract class that combines repetitive code
 * in subclass chess piece tests.
 */
abstract class AbstractChessPieceTest {
  protected boolean[][] results;

  /**
   * Sets up the results of the chess board.
   */
  @Before
  public void setup() {
    results = new boolean[8][8];
  }

  protected void verifyMoveResults(ChessPiece piece) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i == piece.getRow()) && (j == piece.getColumn())) {
          continue;
        }
        assertEquals("Piece at :" + piece.getRow() + ","
                + piece.getColumn() + ", Unexpected canMove result "
                + "for " + "i=" + i + " j=" + j
                , results[i][j], piece.canMove(i, j));
      }
    }
  }

  protected abstract ChessPiece createChessPiece(int row, int col, Color color);

  protected void verifyKillResults(ChessPiece piece) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i == piece.getRow()) && (j == piece.getColumn())) {
          continue;
        }
        ChessPiece another = createChessPiece(i, j,
                Color.values()[(piece.getColor().ordinal() + 1) % Color.values().length]);
        assertEquals("Unexpected canKill result for " + "i=" + i
                + " j=" + j, results[i][j],
                piece.canKill(another));
      }
    }
  }

  /**
   * Tests the getRow(), getColumn(), and getColor().
   */
  @Test(timeout = 500)
  public void testGetters() {
    ChessPiece piece;
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        for (Color c : Color.values()) {
          piece = createChessPiece(row, col, c);
          assertEquals("Row number does not match what was initialized", row, piece.getRow());
          assertEquals("Column number does not match what was initialized", col, piece.getColumn());
          assertEquals("solution.Color does not match what was initialized", c, piece.getColor());
        }
      }
    }
  }

  /**
   * Tests for exception thrown by invalid constructions.
   */
  @Test(timeout = 500)
  public void testInvalidConstructions() {
    ChessPiece piece;
    for (Color c : Color.values()) {
      for (int i = 0; i < 8; i++) {
        try {
          piece = createChessPiece(i, -1, c);
          fail("Did not throw an exception when rook is created with invalid " + "row");
        } catch (IllegalArgumentException e) {
          //passes
        }
        try {
          piece = createChessPiece(-1, i, c);
          fail("Did not throw an exception when rook is created with invalid " + "column");
        } catch (IllegalArgumentException e) {
          //passes
        }
      }
    }
  }

  protected void initializeResults() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        results[row][col] = false;
      }
    }
  }

  protected abstract void setupResults(int row, int col);

  /**
   * Tests canMove().
   */
  @Test(timeout = 500)
  public void testPieceMoves() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        initializeResults();
        ChessPiece piece = createChessPiece(row, col, Color.BLACK);
        setupResults(row, col);
        verifyMoveResults(piece);
      }
    }
  }

  /**
   * Tests canKill().
   */
  @Test(timeout = 500)
  public void testPieceKills() {
    for (Color c : Color.values()) {
      for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
          initializeResults();
          ChessPiece piece = createChessPiece(row, col, c);
          setupResults(row, col);
          verifyKillResults(piece);
        }
      }
    }
  }
}
