package calculator;

/**
 * This interface represents a single calculator where one can
 * input a character into the calculator and get the current result
 * of the calculator.
 */
public interface Calculator {

  /**
   * Method to input a character into the calculator. Only takes
   * a single character at a time and returns a Calculator object after
   * processing the result of inputting the character.
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
