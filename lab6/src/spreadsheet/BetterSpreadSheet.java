package spreadsheet;

/**
 * This interface represents a better spreadsheet that can fill
 * a rectangular area of cells with a value.
 */
public interface BetterSpreadSheet extends SpreadSheet {

  /**
   * Method to fill a rectangular area of cells with a value.
   * @param row1 row coordinate of the first cell
   * @param col1 column coordinate of the first cell
   * @param row2 row coordinate of the last cell
   * @param col2 col coordinate of the last cell
   * @param value value to be put in all cells of the rectangular area
   */
  void setRectangle(int row1, int col1, int row2, int col2, double value);
}
