package calculator;

/**
 * This interface represents a single calculator.
 */
public interface Calculator {

  /**
   * Method to input a character into the calculator.
   *
   * @param c single character for each input.
   * @return a Calculator object as a result of processing the input.
   */
  Calculator input(char c);

  /**
   * Gets the current "result" of the calculator.
   *
   * @return a String object representation of the result.
   */
  String getResult();
}
