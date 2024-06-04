package spreadsheet;

/**
 * An interface that represents a spreadsheet that supports macros.
 */
public interface SpreadSheetWithMacro extends SpreadSheet {
  /**
   * Takes in an object of SpreadSheetMacro and executes it onto a spreadsheet.
   *
   * @param m SpreadSheetMacro to be executed.
   */
  void execute(SpreadSheetMacro m);
}
