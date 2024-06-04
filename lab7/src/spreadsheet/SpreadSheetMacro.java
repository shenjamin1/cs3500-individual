package spreadsheet;

/**
 * A "command" interface that represents a macro that will be given to
 * a spreadsheet as a function object.
 */
public interface SpreadSheetMacro {
  /**
   * Takes in a SpreadSheet object and executes this command on it.
   *
   * @param spreadsheet SpreadSheet object command is executed on.
   */
  void command(SpreadSheet spreadsheet);
}
