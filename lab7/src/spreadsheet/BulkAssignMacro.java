package spreadsheet;

/**
 * A class that represents a macro that bulk assigns a area
 * from one cell to another.
 */
public class BulkAssignMacro implements SpreadSheetMacro {
  private final int row1;
  private final int row2;
  private final int col1;
  private final int col2;
  private final double value;

  /**
   * Constructor for the BulkAssignMacro.
   *
   * @param row1 starting row of the area.
   * @param col1 starting column of the area.
   * @param row2 end row of the area.
   * @param col2 end column of the area.
   * @param value value to be assigned to each cell in the area.
   */
  public BulkAssignMacro(int row1, int col1, int row2, int col2, double value) {
    this.row1 = row1;
    this.col1 = col1;
    this.row2 = row2;
    this.col2 = col2;
    this.value = value;
  }

  @Override
  public void command(SpreadSheet spreadsheet) {
    if (this.row1 > this.row2 || this.col1 > this.col2) {
      throw new IllegalArgumentException("Invalid range.");
    }
    for (int row = row1; row < row2 + 1; row++) {
      for (int col = col1; col < col2 + 1; col++) {
        spreadsheet.set(row, col, value);
      }
    }
  }
}
