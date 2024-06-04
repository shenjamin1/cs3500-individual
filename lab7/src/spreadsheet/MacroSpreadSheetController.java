package spreadsheet;

import java.util.Scanner;

/**
 * This class represents the controller of an interactive spreadsheet application.
 * This controller offers a simple text interface in which the user can
 * type instructions to manipulate a spreadsheet.
 *
 * <p>This controller works with any Readable to read its inputs and
 * any Appendable to transmit output. This controller directly uses
 * the Appendable object (i.e. there is no official "view")
 *
 * <p>A cell in the spreadsheet is referred to using a row-letter and a column number.
 * The row letter starts from A-Z and then AA-ZZ, then AAA-ZZZ and so on.
 * The column numbers begin with 1.
 *
 * <p>For example, the cell in the first row and column is A 1.
 * The cell in the 30th row and 26th column is AD 26.
 *
 * <p>In this way it tries to simulate how Microsoft Excel works (except that
 * it uses letters for rows, not columns).
 */
public class MacroSpreadSheetController extends SpreadSheetController {
  /**
   * Create a controller to work with the specified sheet (model),
   * readable (to take inputs) and appendable (to transmit output).
   *
   * @param sheet      the sheet to work with (the model)
   * @param readable   the Readable object for inputs
   * @param appendable the Appendable objects to transmit any output
   */
  public MacroSpreadSheetController(SpreadSheet sheet, Readable readable, Appendable appendable) {
    super(sheet, readable, appendable);
  }

  @Override
  protected void processCommand(String userInstruction, Scanner sc, SpreadSheet sheet) {
    switch (userInstruction) {
      case "bulk-assign-value":
        try {
          int row1 = getRowNum(sc.next());
          int col1 = sc.nextInt();
          int row2 = getRowNum(sc.next());
          int col2 = sc.nextInt();
          double value = sc.nextDouble();
          System.out.println("Setting cells (" + row1 + "," + (col1 - 1)
                  + ") to (" + row2 + "," + (col2 - 1) + ")");
          SpreadSheetMacro bulkAssignMacro = new BulkAssignMacro(
                  row1, col1 - 1, row2, col2 - 1, value);
          ((SpreadSheetWithMacroImpl) sheet).execute(bulkAssignMacro);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      default:
        super.processCommand(userInstruction, sc, sheet);
    }
  }

  protected void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: " + System.lineSeparator());
    writeMessage("assign-value row-num col-num value (set a cell to a value)"
            + System.lineSeparator());
    writeMessage("print-value row-num col-num (print the value at a given cell)"
            + System.lineSeparator());
    writeMessage("bulk-assign-value from-row from-col-num to-row to-col-num value" +
            "(bulk assign from one cell to another)" + System.lineSeparator());
    writeMessage("menu (Print supported instruction list)" + System.lineSeparator());
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
  }
}
