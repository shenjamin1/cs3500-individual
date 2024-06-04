package calculator;

/**
 * Simple implementation of the Calculator interface that takes straightforward
 * inputs of 32 bits or fewer and operates on them with +, -, or *.
 */
public class SimpleCalculator extends AbstractCalculator {
  /**
   * Constructs a SimpleCalculator and initializes the result StringBuilder,
   * operands, operator, and operator index.
   */
  public SimpleCalculator() {
    super();
  }

  private SimpleCalculator(StringBuilder result, int operand1, int operand2,
                           char operator, int operatorIndex) {
    super(result, operand1, operand2, operator, operatorIndex);
  }

  @Override
  protected Calculator helpClearInput() {
    return new SimpleCalculator(new StringBuilder(), 0, 0,
            '\0', -1);
  }

  @Override
  protected Calculator helpEqualInput() {
    if (result.toString().isEmpty()) {
      throw new IllegalArgumentException("Cannot input = right now.");
    }
    if (operator == '=') {
      return this;
    }
    int newOperand2 = Integer.parseInt(result.substring(operatorIndex));
    int resultNum = calculate(operand1, newOperand2, operator);
    return new SimpleCalculator(new StringBuilder(Integer.toString(resultNum)),
            0, 0, '=', -1);
  }

  @Override
  protected Calculator helpDigitInput(char c) throws IllegalArgumentException {
    StringBuilder newResult = new StringBuilder(result).append(c);
    if (operator == '=') {
      newResult = new StringBuilder();
      newResult.append(c);
    }
    int newOperand1 = operand1;
    int newOperand2 = operand2;
    if (operatorIndex == -1) {
      newOperand1 = Integer.parseInt(newResult.toString());
      if (Integer.toString(newOperand1).length() > 10) {
        throw new IllegalArgumentException("Operand overflow.");
      }
    } else {
      if (newResult.indexOf(String.valueOf(operator)) + 2 < newResult.length()) {
        newOperand2 = Integer.parseInt(String.valueOf(newResult.charAt(newResult.length() - 1)));
      } else {
        newOperand2 = Integer.parseInt(newResult.substring(operatorIndex));
      }
      if (Integer.toString(newOperand2).length() > 10) {
        throw new IllegalArgumentException("Operand overflow.");
      }
    }
    return new SimpleCalculator(newResult, newOperand1, newOperand2, operator, operatorIndex);
  }

  @Override
  protected Calculator helpOperatorInput(char c) {
    if (result.toString().isEmpty() || isOperator(result.charAt(result.length() - 1))) {
      throw new IllegalArgumentException("Cannot input an operator right now.");
    }
    StringBuilder newResult = new StringBuilder(result);
    int newOperand1 = operand1;
    int newOperand2 = operand2;
    char newOperator = operator;
    int newOperatorIndex = operatorIndex;

    if (newOperatorIndex != -1) {
      newOperand2 = Integer.parseInt(result.substring(newOperatorIndex));
      int resultNum = calculate(newOperand1, newOperand2, newOperator);
      newResult = new StringBuilder(Integer.toString(resultNum));
      newOperand1 = resultNum;
      newResult.append(c);
    } else {
      newOperator = c;
      newOperand1 = Integer.parseInt(result.toString());
      newResult.append(c);
      newOperatorIndex = newResult.length();
    }
    return new SimpleCalculator(newResult, newOperand1, newOperand2,
            newOperator, newOperatorIndex);
  }
}
