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
    int row1;
    int col1;
    int row2;
    int col2;
    SpreadSheetMacro macro;
    switch (userInstruction) {
      case "bulk-assign-value":
        try {
          row1 = getRowNum(sc.next());
          col1 = sc.nextInt();
          row2 = getRowNum(sc.next());
          col2 = sc.nextInt();
          double value = sc.nextDouble();
          System.out.println("Setting cells (" + row1 + "," + (col1 - 1)
                  + ") to (" + row2 + "," + (col2 - 1) + ")");
          macro = new BulkAssignMacro(
                  row1, col1 - 1, row2, col2 - 1, value);
          ((SpreadSheetWithMacroImpl) sheet).execute(macro);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;

      case "average":
        try {
          row1 = getRowNum(sc.next());
          col1 = sc.nextInt();
          row2 = getRowNum(sc.next());
          col2 = sc.nextInt();
          int destRow = getRowNum(sc.next());
          int destCol = sc.nextInt();
          System.out.println("Getting average of cells (" + row1 + "," + (col1 - 1)
                  + ") to (" + row2 + "," + (col2 - 1) + ")");
          macro = new AverageMacro(row1, col1, row2, col2, destRow, destCol);
          ((SpreadSheetWithMacroImpl) sheet).execute(macro);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;

      case "range-assign":
        try {
          row1 = getRowNum(sc.next());
          col1 = sc.nextInt(0);
          row2 = getRowNum(sc.next());
          col2 = sc.nextInt();
          double startValue = sc.nextDouble();
          double increment = sc.nextDouble();
          System.out.println("Setting incremental range of cells (" + row1 + "," + (col1 - 1)
                  + ") to (" + row2 + "," + (col2 - 1) + ")");
          macro = new RangeAssignMacro(row1, col1, row2, col2, startValue, increment);
          ((SpreadSheetWithMacroImpl) sheet).execute(macro);
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
    writeMessage("bulk-assign-value from-row from-col-num to-row to-col-num value"
            + "(bulk assign from one cell to another)" + System.lineSeparator());
    writeMessage("average from-row-num from-col-num to-row-num to-col-num dest-row-num dest-col-num"
            + "(get average of cells in a range)" + System.lineSeparator());
    writeMessage("range-assign from-row-num from-col-num to-row-num "
            + "to-col-num start-value increment"
            + "(assign a range of values that increment by a given"
            + "increment to a range in a row or column of cells)" + System.lineSeparator());
    writeMessage("menu (Print supported instruction list)" + System.lineSeparator());
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
  }
}
