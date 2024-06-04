import org.junit.Test;

import calculator.Calculator;
import calculator.SmartCalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Testing class for SmartCalculator implementation of Calculator.
 */
public class SmartCalculatorTest extends AbstractCalculatorTest {

  @Override
  protected Calculator createCalculator() {
    return new SmartCalculator();
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
    assertEquals("64", calc9.getResult());
    assertEquals("73", calc9.input('=').getResult());
  }

  @Override
  public void testOperatorFirstInput() {
    Calculator calc2 = calc.input('+').input('3').input('2').input('-')
            .input('2').input('4');
    assertEquals("32-24", calc2.getResult());
    assertEquals("8", calc2.input('=').getResult());

    assertThrows(IllegalArgumentException.class, () -> {
      calc.input('*').input('3').input('2').input('-')
              .input('2').input('4').input('=');
    });
  }

  @Override
  public void testNoOperand2() {
    Calculator calc2 = calc.input('3').input('2').input('+');
    assertEquals("32+", calc2.getResult());
    Calculator calc3 = calc2.input('=');
    assertEquals("64", calc3.getResult());
    assertEquals("96", calc3.input('=').getResult());
  }

  @Override
  public void testConsecutiveOperators() {
    Calculator calc2 = calc.input('3').input('2').input('+')
            .input('-').input('2').input('4');
    assertEquals("32-24", calc2.getResult());
    assertEquals("8", calc2.input('=').getResult());
  }

  @Test
  public void testNewOperandAfterEquals() {
    Calculator calc2 = calc.input('3').input('2').input('-')
            .input('2').input('4').input('=');
    assertEquals("8", calc2.getResult());
    assertEquals("6", calc2.input('6').getResult());
  }

  @Test
  public void testOperatorOperandAfterResult() {
    Calculator calc2 = calc.input('1').input('*').input('3').input('=');
    assertEquals("3", calc2.getResult());
    Calculator calc3 = calc2.input('+');
    assertEquals("3+", calc3.getResult());
    assertEquals("4", calc3.input('1').input('=').getResult());
  }

  @Test
  public void testMultipleOperators() {
    assertEquals("3+", calc.input('3').input('-').input('*')
            .input('+').getResult());
  }
}
