import org.junit.Before;
import org.junit.Test;

import spreadsheet.AverageMacro;
import spreadsheet.BulkAssignMacro;
import spreadsheet.RangeAssignMacro;
import spreadsheet.SpreadSheet;
import spreadsheet.SpreadSheetMacro;
import spreadsheet.SpreadSheetWithMacro;
import spreadsheet.SpreadSheetWithMacroImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * This class is a tester for various SpreadSheet macros.
 */
public class SpreadSheetMacroTest {
  private SpreadSheetMacro macro;
  private SpreadSheetMacro invalidMacro;
  private SpreadSheetWithMacro spreadsheet;

  @Before
  public void setup() {
    spreadsheet = new SpreadSheetWithMacroImpl();
  }

  @Test
  public void testBulkAssignMacro() {
    // test normal
    macro = new BulkAssignMacro(0, 1, 1, 3, 100);
    spreadsheet.execute(macro);
    for (int row = 0; row < 2; row++) {
      for (int col = 1; col < 4; col++) {
        assertEquals(100, spreadsheet.get(row, col), .001);
      }
    }

    // test exception
    assertThrows(IllegalArgumentException.class, () -> {
      invalidMacro = new BulkAssignMacro(3, 1, 1, 0, 100);
    });
  }

  @Test
  public void testAverageMacro() {
    //test normal
    macro = new BulkAssignMacro(0, 1, 1, 3, 100);
    spreadsheet.execute(macro);
    spreadsheet.set(0, 4, 90);
    spreadsheet.set(1, 4, 110);
    macro = new AverageMacro(0, 1, 1, 4, 2, 1);
    spreadsheet.execute(macro);
    assertEquals(100, spreadsheet.get(2, 1), .001);

    // test exception
    assertThrows(IllegalArgumentException.class, () -> {
      macro = new AverageMacro(0, 1, 1, 4, -1, 0);
    });
  }

  @Test
  public void testRangeAssignMacro() {
    // test normal
    macro = new RangeAssignMacro(0, 1, 0, 10, 1, 1);
    spreadsheet.execute(macro);
    for (int row = 0; row < 1; row++) {
      for (int col = 0; col < 11; col++) {
        assertEquals(col, spreadsheet.get(row, col), .001);
      }
    }

    // test exception
    assertThrows(IllegalArgumentException.class, () -> {
      macro = new RangeAssignMacro(0, 1, 10, 2, 1, 1);
    });
  }
}
