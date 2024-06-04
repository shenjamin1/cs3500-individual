import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import spreadsheet.BetterSparseSpreadSheet;
import spreadsheet.BetterSpreadSheet;
import spreadsheet.MockBetterSpreadSheet;
import spreadsheet.SpreadSheetController;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for the spreadsheet controller.
 */
public class SpreadSheetControllerTest {
  private BetterSpreadSheet model;
  private Readable in;
  private Appendable out;
  private SpreadSheetController controller;

  /**
   * Setup for each test.
   */
  @Before
  public void setup() {
    out = new StringBuilder();
  }

  /**
   * Tests that the program outputs the right welcome message.
   */
  @Test
  public void testWelcomeMessage() {
    model = new BetterSparseSpreadSheet();
    in = new StringReader("q");
    controller = new SpreadSheetController(model, in, out);
    controller.execute();
    String welcomeMessage = "Welcome to the spreadsheet program!\n" +
            "Supported user instructions are: \n" +
            "assign-value row-num col-num value (set a cell to a value)\n" +
            "print-value row-num col-num (print the value at a given cell)\n" +
            "menu (Print supported instruction list)\n" +
            "q or quit (quit the program) ";
    String[] welcomeMessageArray = welcomeMessage.split("\n");
    String[] output = out.toString().split("\n");
    String[] welcomeOutput = new String[6];
    System.arraycopy(output, 0, welcomeOutput, 0, 6);
    assertEquals(welcomeOutput, welcomeMessageArray);
  }

  /**
   * Tests that the program outputs the right farewell message.
   */
  @Test
  public void testFarewellMessage() {
    model = new BetterSparseSpreadSheet();
    in = new StringReader("q");
    controller = new SpreadSheetController(model, in, out);
    controller.execute();
    String[] output = out.toString().split("\n");
    assertEquals("Thank you for using this program!", output[output.length - 1]);
  }

  /**
   * Tests that the controller is sending the right inputs for get() with a mock.
   */
  @Test
  public void testGetInput() {
    StringBuilder log = new StringBuilder();
    model = new MockBetterSpreadSheet(log);
    in = new StringReader("assign-value A 1 1 print-value A 1 q");
    controller = new SpreadSheetController(model, in, out);
    controller.execute();
    assertEquals("set: row = 0, col = 0, value = 1.00\nget: row = 0, col = 0\n",
            log.toString());
  }

  /**
   * Tests that the controller is sending the right inputs for set() with a mock.
   */
  @Test
  public void testSetInput() {
    StringBuilder log = new StringBuilder();
    model = new MockBetterSpreadSheet(log);
    in = new StringReader("assign-value A 1 1 q");
    controller = new SpreadSheetController(model, in, out);
    controller.execute();
    assertEquals("set: row = 0, col = 0, value = 1.00\n", log.toString());
  }

  /**
   * Tests that the controller is sending the right inputs for setRectangle() with a mock.
   */
  @Test
  public void testSetRectangleInput() {
    StringBuilder log = new StringBuilder();
    model = new MockBetterSpreadSheet(log);
    in = new StringReader("bulk-assign A 1 B 3 100 q");
    controller = new SpreadSheetController(model, in, out);
    controller.execute();
    assertEquals("setRectangle: row1 = 0, col1 = 1, row2 = 1, col2 = 3, value = 100\n",
            log.toString());
  }

  /**
   * Tests that setRectangle() works as intended for the BetterSparseSpreadSheet.
   */
  @Test
  public void testSetRectangle() {
    model = new BetterSparseSpreadSheet();
    model.setRectangle(0, 1, 1, 3, 100);
    for (int row = 0; row < 2; row++) {
      for (int col = 1; col < 4; col++) {
        assertEquals(100, model.get(row, col), 0.0001);
      }
    }
  }
}