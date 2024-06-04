package spreadsheet;

import java.util.Objects;

/**
 * Class that mocks a spreadsheet.
 */
public class MockSpreadSheet implements SpreadSheet {
  final StringBuilder log;

  /**
   * Constructor for MockSpreadSheet that initializes a log.
   * @param log log of activity.
   */
  public MockSpreadSheet(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    log.append(String.format("get: row = %d, col = %d\n", row, col));
    return 0;
  }

  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    log.append(String.format("set: row = %d, col = %d, value = %.3g\n", row, col, value));
  }

  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    log.append(String.format("isEmpty: row = %d, col = %d\n", row, col));
    return false;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }
}
