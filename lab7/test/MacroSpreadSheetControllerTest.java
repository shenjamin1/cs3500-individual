import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import spreadsheet.MacroSpreadSheetController;
import spreadsheet.SpreadSheetController;
import spreadsheet.SpreadSheetWithMacroImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class is a tester for MacroSpreadSheetController.
 */
public class MacroSpreadSheetControllerTest {
  private SpreadSheetWithMacroImpl model;
  private Appendable out;
  private MacroSpreadSheetController controller;

  @Before
  public void setup() {
    model = new SpreadSheetWithMacroImpl();
    out = new StringBuilder();
  }

  @Test
  public void testBulkAssignMacro() {
    StringBuilder input = new StringBuilder();
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to the spreadsheet program!").append(System.lineSeparator());
    expected.append("Supported user instructions are: ").append(System.lineSeparator());
    expected.append("assign-value row-num col-num value (set a cell to a value)")
            .append(System.lineSeparator());
    expected.append("print-value row-num col-num (print the value at a given cell)")
            .append(System.lineSeparator());
    expected.append("bulk-assign-value from-row-num from-col-num to-row-num to-col-num value")
            .append(" (set a range of cells to a value)").append(System.lineSeparator());
    expected.append("range-assign from-row-num from-col-num to-row-num to-col-num start-value")
            .append(" increment (set a row or column of cells to a range of values starting at the")
            .append(" given value and advancing by the increment)").append(System.lineSeparator());
    expected.append("average from-row-num from-col-num to-row-num to-col-num dest-row-num ")
            .append("dest-col-num (compute the average of a range of cells and put it at the given")
            .append(" location)").append(System.lineSeparator());
    expected.append("menu (Print supported instruction list)").append(System.lineSeparator());
    expected.append("q or quit (quit the program) ").append(System.lineSeparator());
    expected.append("Type instruction: ");
    input.append("bulk-assign-value " + 'A' + " " + '1' + " " + 'B' + " " + '3' + " 100")
            .append(System.lineSeparator());
    for (int row = 0; row < 2; row++) {
      for (int col = 1; col < 4; col++) {
        expected.append("Type instruction: ");
        input.append("print-value ").append((char) ('A' + row)).append(" ").append(col)
                .append(System.lineSeparator());
        expected.append("Value: 100.0").append(System.lineSeparator());
      }
    }
    expected.append("Thank you for using this program!");
    Readable in = new StringReader(input.toString());
    SpreadSheetController controller = new MacroSpreadSheetController(model, in, out);
    controller.control();
    assertEquals(expected.toString(), out.toString());
  }
}
