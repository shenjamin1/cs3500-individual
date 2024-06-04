package spreadsheet;

import java.io.InputStreamReader;

/**
 * Executes the spreadsheet program.
 */
public class SpreadSheetProgram {
  /**
   * Main method.
   * @param args String[] args for main method
   */
  public static void main(String[] args) {
    BetterSpreadSheet model = new BetterSparseSpreadSheet();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    SpreadSheetController controller = new SpreadSheetController(model,rd,ap);
    controller.execute();
  }
}
