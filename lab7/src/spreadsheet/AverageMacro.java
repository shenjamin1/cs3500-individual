package spreadsheet;

/**
 * A class that represents a macro that gets the average
 * of cells in an area and puts that average in a designated cell.
 */
public class AverageMacro implements SpreadSheetMacro {
  private final int row1;
  private final int col1;
  private final int row2;
  private final int col2;
  private final int destRow;
  private final int destCol;

  /**
   * Constructor for an AverageMacro.
   *
   * @param row1 starting row of the area.
   * @param col1 starting column of the area.
   * @param row2 end row of the area.
   * @param col2 end column of the area.
   * @param destRow row of the designated cell.
   * @param destCol column of the designated cell.
   */
  public AverageMacro(int row1, int col1, int row2, int col2, int destRow, int destCol) {
    this.row1 = row1;
    this.col1 = col1;
    this.row2 = row2;
    this.col2 = col2;
    this.destRow = destRow;
    this.destCol = destCol;
    if (this.row1 > this.row2 || this.col1 > this.col2) {
      throw new IllegalArgumentException("Invalid range.");
    }
    if (destRow < 0 || destCol < 1) {
      throw new IllegalArgumentException("Invalid destination.");
    }
  }

  @Override
  public void command(SpreadSheet spreadsheet) {
    double sum = 0;
    int count = 0;
    for (int row = row1; row < row2 + 1; row++) {
      for (int col = col1; col < col2 + 1; col++) {
        sum += spreadsheet.get(row, col);
        count++;
      }
    }
    spreadsheet.set(destRow, destCol, sum / count);
  }
}
