package spreadsheet;

/**
 * Class that mocks a better spreadsheet.
 */
public class MockBetterSpreadSheet extends MockSpreadSheet implements BetterSpreadSheet {
  /**
   * Constructor for MockBetterSpreadSheet that initializes a log.
   *
   * @param log log of activity.
   */
  public MockBetterSpreadSheet(StringBuilder log) {
    super(log);
  }

  @Override
  public void setRectangle(int row1, int col1, int row2, int col2, double value) {
    this.log.append(String.format(
            "setRectangle: row1 = %d, col1 = %d, row2 = %d, col2 = %d, value = %.3g\n",
            row1, col1, row2, col2, value));
  }
}
