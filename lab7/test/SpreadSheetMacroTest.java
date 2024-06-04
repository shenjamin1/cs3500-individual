import org.junit.Before;
import org.junit.Test;

import spreadsheet.BulkAssignMacro;
import spreadsheet.SpreadSheetMacro;
import spreadsheet.SpreadSheetWithMacro;
import spreadsheet.SpreadSheetWithMacroImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * This class is a tester for various SpreadSheet macros.
 */
public class SpreadSheetMacroTest {
  private SpreadSheetWithMacro spreadsheet;

  @Before
  public void setup() {
    spreadsheet = new SpreadSheetWithMacroImpl();
  }

  @Test
  public void testBulkAssignMacro() {
    // test normal
    SpreadSheetMacro macro = new BulkAssignMacro(0, 1, 1, 3, 100);
    spreadsheet.execute(macro);
    for (int row = 0; row < 2; row++) {
      for (int col = 1; col < 4; col++) {
        assertEquals(100, spreadsheet.get(row, col), .001);
      }
    }

    // test exception
    SpreadSheetMacro invalidMacro = new BulkAssignMacro(3, 1, 1, 0, 100);
    assertThrows(IllegalArgumentException.class, () -> {
      spreadsheet.execute(invalidMacro);
    });
  }
}
