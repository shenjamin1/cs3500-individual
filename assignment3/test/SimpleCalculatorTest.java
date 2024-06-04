import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Testing class for SimpleCalculator implementation of Calculator.
 */
public class SimpleCalculatorTest extends AbstractCalculatorTest {

  @Override
  protected Calculator createCalculator() {
    return new SimpleCalculator();
  }

  @Override
  public void testInputComplexSequenceAddition() {
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

  @Override
  public void testOperatorFirstInput() {
    assertThrows(IllegalArgumentException.class, () -> {
      calc.input('+').input('2').input('-').input('1').input('=');
    });
  }

  @Override
  public void testNoOperand2() {
    assertThrows(IllegalArgumentException.class, () -> {
      calc.input('1').input('2').input('+').input('=');
    });
  }

  @Override
  public void testConsecutiveOperators() {
    assertThrows(IllegalArgumentException.class, () -> {
      calc.input('2').input('+').input('-').input('1').input('=');
    });
  }

  @Test
  public void testOverflowSubtract() {
    Calculator calc2 = calc.input('1').input('1')
            .input('1').input('1').input('1').input('1').input('1')
            .input('1').input('1').input('-').input('1').input('0')
            .input('3').input('6').input('3').input('7').input('2')
            .input('5').input('3').input('8');
    assertEquals("111111111-1036372538", calc2.getResult());
    calc2.input('=');
  }
}