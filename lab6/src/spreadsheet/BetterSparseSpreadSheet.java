package spreadsheet;

/**
 * This class represents a better sparse spreadsheet. A better sparse spreadsheet is a spreadsheet
 * with a large number of empty cells. It represents this efficiently using a hash map.
 *
 */
public class BetterSparseSpreadSheet extends SparseSpreadSheet implements BetterSpreadSheet {
  @Override
  public void setRectangle(int row1, int col1, int row2, int col2, double value) {
    if (row1 > row2 || col1 > col2) {
      throw new IllegalArgumentException("Invalid rectangle coordinates.");
    }
    for (int row = row1; row < row2 + 1; row++) {
      for (int col = col1; col < col2 + 1; col++) {
        this.set(row, col, value);
      }
    }
  }
}
