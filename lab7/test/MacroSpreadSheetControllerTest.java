import org.junit.Before;
import org.junit.Test;

import spreadsheet.MacroSpreadSheetController;
import spreadsheet.SpreadSheetWithMacroImpl;

public class MacroSpreadSheetControllerTest {
  private SpreadSheetWithMacroImpl model;
  private Readable in;
  private Appendable out;
  private MacroSpreadSheetController controller;

  @Before
  public void setup() {
    model = new SpreadSheetWithMacroImpl();
    out = new StringBuilder();
  }

  
}
