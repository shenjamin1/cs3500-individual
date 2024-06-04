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
    expected.append("Welcome to the spreadsheet program!").append(System.lineSeparator())
            .append("Supported user instructions are: ").append(System.lineSeparator())
            .append("assign-value row-num col-num value (set a cell to a value)")
            .append(System.lineSeparator())
            .append("print-value row-num col-num (print the value at a given cell)")
            .append(System.lineSeparator())
            .append("bulk-assign-value from-row-num from-col-num to-row to-col-num value "
                    + "(bulk assign from one cell to another)")
            .append(System.lineSeparator())
            .append("average from-row-num from-col-num to-row-num to-col-num "
                    + "dest-row-num dest-col-num "
                    + "(get average of cells in a range)")
            .append(System.lineSeparator())
            .append("range-assign from-row-num from-col-num to-row-num "
                    + "to-col-num start-value increment "
                    + "(assign a range of values that increment by a given "
                    + "increment to a range in a row or column of cells)")
            .append(System.lineSeparator())
            .append("menu (Print supported instruction list)")
            .append(System.lineSeparator()).append("q or quit (quit the program) ")
            .append(System.lineSeparator()).append("Type instruction: ");
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
