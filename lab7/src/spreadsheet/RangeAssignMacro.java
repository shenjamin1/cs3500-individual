package spreadsheet;

/**
 * Class that represents a macro that assigns values that increment by a given
 * increment to a range in a row or column.
 */
public class RangeAssignMacro implements SpreadSheetMacro {
  private final int row1;
  private final int col1;
  private final int row2;
  private final int col2;
  private final double startValue;
  private final double increment;

  /**
   * Constructor for RangeAssignMacro.
   *
   * @param row1 starting row of the area.
   * @param col1 starting column of the area.
   * @param row2 end row of the area.
   * @param col2 end column of the area.
   * @param startValue starting value.
   * @param increment number to increment value by each time.
   */
  public RangeAssignMacro(int row1, int col1, int row2, int col2,
                          double startValue, double increment) {
    this.row1 = row1;
    this.col1 = col1;
    this.row2 = row2;
    this.col2 = col2;
    this.startValue = startValue;
    this.increment = increment;
    if (this.row1 > this.row2 || this.col1 > this.col2) {
      throw new IllegalArgumentException("Invalid range.");
    }
    if (row1 != row2 && col1 != col2) {
      throw new IllegalArgumentException("Range must be a row or column.");
    }
  }

  @Override
  public void command(SpreadSheet spreadsheet) {
    double value = startValue;
    for (int row = row1; row < row2 + 1; row++) {
      for (int col = col1; col < col2 + 1; col++) {
        spreadsheet.set(row, col, value);
        value += increment;
      }
    }
  }
}
