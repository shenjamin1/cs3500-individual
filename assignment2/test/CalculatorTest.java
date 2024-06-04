import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Testing class for Calculator.
 */
public class CalculatorTest {
  private SimpleCalculator calc;

  @Before
  public void setup() {
    calc = new SimpleCalculator();
  }

  @Test
  public void testInputSimpleAddition() {
    Calculator calc1 = calc.input('1');
    assertEquals("1", calc1.getResult());
    Calculator calc2 = calc1.input('2');
    assertEquals("12", calc2.getResult());
    Calculator calc3 = calc2.input('+');
    assertEquals("12+", calc3.getResult());
    Calculator calc4 = calc3.input('3');
    assertEquals("12+3", calc4.getResult());
    Calculator calc5 = calc4.input('4');
    assertEquals("12+34", calc5.getResult());
    Calculator calc6 = calc5.input('=');
    assertEquals("46", calc6.getResult());
  }

  @Test
  public void testInputComplexAddition() {
    Calculator calc1 = calc.input('1');
    assertEquals("1", calc1.getResult());
    Calculator calc2 = calc1.input('2');
    assertEquals("12", calc2.getResult());
    Calculator calc3 = calc2.input('+');
    assertEquals("12+", calc3.getResult());
    Calculator calc4 = calc3.input('3');
    assertEquals("12+3", calc4.getResult());
    Calculator calc5 = calc4.input('4');
    assertEquals("12+34", calc5.getResult());
    Calculator calc6 = calc5.input('+');
    assertEquals("46+", calc6.getResult());
    Calculator calc7 = calc6.input('9');
    assertEquals("46+9", calc7.getResult());
    Calculator calc8 = calc7.input('=');
    assertEquals("55", calc8.getResult());

    // test for multiple equals
    Calculator calc9 = calc8.input('=');
    assertEquals("55", calc9.getResult());
  }

  @Test
  public void testInputSimpleSubtraction() {
    Calculator calc1 = calc.input('7').input('1').input('1').input('3').input('-')
            .input('1').input('2').input('3').input('8').input('0').input('=');
    assertEquals("-5267", calc1.getResult());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFirstInput() {
    calc.input('L');
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSecondInput() {
    calc.input('1');
    calc.input('L');
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOperatorFirstInput() {
    calc.input('+').input('2').input('-').input('1').input('=');
  }

  @Test
  public void testOperandOverflow() {
    Calculator calc1 = calc.input('1').input('1').input('1').input('1').input('1').input('1')
            .input('1').input('1').input('1').input('1');
    assertEquals("1111111111", calc1.getResult());
    assertThrows(IllegalArgumentException.class, () -> calc1.input('1'));
    assertEquals("1111111111", calc1.getResult());
  }

  @Test
  public void testArithmeticOverflow() {
    Calculator calc1 = calc.input('2').input('1').input('4').input('7').input('4').input('8')
            .input('3').input('6').input('4').input('7').input('*').input('2').input('=');
    assertEquals("0", calc1.getResult());
  }

  @Test
  public void testCWorks() {
    Calculator calc1 = calc.input('2').input('1').input('-').input('1').input('=');
    assertEquals("20", calc1.getResult());
    Calculator calc2 = calc1.input('C');
    assertEquals("", calc2.getResult());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoOperand2() {
    calc.input('1').input('2').input('+').input('=');
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConsecutiveOperators() {
    calc.input('1').input('+').input('-');
  }
}
