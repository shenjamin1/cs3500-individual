package spreadsheet;

/**
 * Implements the SpreadSheetWithMacro interface and reuses a provided SparseSpreadSheet
 * implementation.
 */
public class SpreadSheetWithMacroImpl extends SparseSpreadSheet implements SpreadSheetWithMacro {
  @Override
  public void execute(SpreadSheetMacro m) {
    m.command(this);
  }
}
