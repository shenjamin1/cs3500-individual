package spreadsheet;

public class BulkAssignMacro implements SpreadSheetMacro {
  private final int row1;
  private final int row2;
  private final int col1;
  private final int col2;
  private final double value;

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
