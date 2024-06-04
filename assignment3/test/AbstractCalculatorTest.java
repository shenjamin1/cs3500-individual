import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Abstract class that abstracts tests common to both
 * implementations.
 */
abstract class AbstractCalculatorTest {
  Calculator calc;

  protected abstract Calculator createCalculator();

  @Before
  public void setup() {
    calc = createCalculator();
  }

  @Test
  public void testInputSimpleSequence() {
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
  public abstract void testInputComplexSequenceAddition();

  @Test
  public void testInputNegativeHandling() {
    Calculator calc1 = calc.input('7').input('1').input('1').input('3')
            .input('-').input('1').input('2').input('3').input('8')
            .input('0').input('=');
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

  @Test
  public abstract void testOperatorFirstInput();

  @Test
  public void testOperandOverflow() {
    Calculator calc1 = calc.input('1').input('1').input('1')
            .input('1').input('1').input('1').input('1')
            .input('1').input('1').input('1');
    assertEquals("1111111111", calc1.getResult());
    assertThrows(IllegalArgumentException.class, () -> calc1.input('1'));
    assertEquals("1111111111", calc1.getResult());
  }

  @Test
  public void testArithmeticOverflow() {
    Calculator calc1 = calc.input('2').input('1').input('4')
            .input('7').input('4').input('8').input('3')
            .input('6').input('4').input('7').input('*')
            .input('2').input('=');
    assertEquals("0", calc1.getResult());
  }

  @Test
  public void testCWorks() {
    Calculator calc1 = calc.input('2').input('1').input('-').input('1').input('=');
    assertEquals("20", calc1.getResult());
    Calculator calc2 = calc1.input('C');
    assertEquals("", calc2.getResult());
    Calculator calc3 = calc2.input('1');
    assertEquals("1", calc3.getResult());
    Calculator calc4 = calc3.input('C');
    assertEquals("", calc4.getResult());
    Calculator calc5 = calc4.input('1').input('-');
    assertEquals("1-", calc5.getResult());
    Calculator calc6 = calc5.input('C');
    assertEquals("", calc6.getResult());
  }

  @Test
  public abstract void testNoOperand2();

  @Test
  public abstract void testConsecutiveOperators();

  @Test
  public void testImmutable() {
    Calculator calc2 = calc.input('1');
    assertEquals("", calc.getResult());
    assertEquals("1", calc2.getResult());
  }
}
