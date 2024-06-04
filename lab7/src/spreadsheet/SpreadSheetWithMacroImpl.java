package spreadsheet;

public class SpreadSheetWithMacroImpl extends SparseSpreadSheet implements SpreadSheetWithMacro {
  @Override
  public void execute(SpreadSheetMacro m) {
    m.command(this);
  }
}
