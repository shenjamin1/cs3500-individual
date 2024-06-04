package calculator;

/**
 * Implementation of Calculator interface that accepts inputs like a normal calculator.
 * It is backwards compatible with the SimpleCalculator implementation, meaning it can handle
 * the same things (see SimpleCalculator documentation). Once again, the inputs are of 32 bits
 * or fewer.
 */
public class SmartCalculator extends AbstractCalculator {
  private final char lastOperator;

  /**
   * Constructs a SmartCalculator and initializes the result StringBuilder,
   * operands, operator, and operator index.
   */
  public SmartCalculator() {
    super();
    this.lastOperator = '\0';
  }

  private SmartCalculator(StringBuilder result, int operand1, int operand2,
                          char operator, int operatorIndex, char lastOperator) {
    super(result, operand1, operand2, operator, operatorIndex);
    this.lastOperator = lastOperator;
  }

  @Override
  protected Calculator helpClearInput() {
    return new SmartCalculator(new StringBuilder(), 0, 0,
            '\0', -1, '\0');
  }

  @Override
  protected Calculator helpEqualInput() {
    if (result.toString().isEmpty()) {
      throw new IllegalArgumentException("Cannot input = right now.");
    }
    if (operator == '=') {
      int resultNum = calculate(operand1, operand2, lastOperator);
      StringBuilder newResult = new StringBuilder(Integer.toString(resultNum));
      int newOperatorIndex = newResult.length();
      return new SmartCalculator(newResult, resultNum, operand2, operator,
              newOperatorIndex, lastOperator);
    }
    int newOperand2;
    if (result.length() == operatorIndex) {
      newOperand2 = operand1;
    } else {
      try {
        newOperand2 = Integer.parseInt(result.substring(operatorIndex));
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Illegal operand.");
      }
    }
    int resultNum = calculate(operand1, newOperand2, operator);
    return new SmartCalculator(new StringBuilder(Integer.toString(resultNum)),
            resultNum, newOperand2, '=', -1, operator);
  }

  @Override
  protected Calculator helpDigitInput(char c) {
    StringBuilder newResult = new StringBuilder(result).append(c);
    if (operator == '=') {
      int resultNum = Integer.parseInt(String.valueOf(c));
      return new SmartCalculator(new StringBuilder(String.valueOf(resultNum)),
              resultNum, 0, '\0', -1, '\0');
    }
    int newOperand1 = operand1;
    int newOperand2 = operand2;
    if (operatorIndex == -1) {
      try {
        newOperand1 = Integer.parseInt(newResult.toString());
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Illegal operand.");
      }
      if (newResult.length() > 10) {
        throw new IllegalArgumentException("Operand overflow.");
      }
    } else {
      try {
        newOperand2 = Integer.parseInt(newResult.substring(operatorIndex));
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Illegal operand.");
      }
      if (newResult.length() - operatorIndex > 10) {
        throw new IllegalArgumentException("Operand overflow.");
      }
    }
    return new SmartCalculator(newResult, newOperand1, newOperand2,
            operator, operatorIndex, operator);
  }

  @Override
  protected Calculator helpOperatorInput(char c) {
    if (c == '+' && result.toString().isEmpty()) {
      return this;
    } else if (c != '+' && result.toString().isEmpty()) {
      throw new IllegalArgumentException("Cannot input an operator right now.");
    }
    StringBuilder newResult = new StringBuilder(result);
    int newOperand1 = operand1;
    int newOperand2 = operand2;
    char newOperator = operator;
    int newOperatorIndex = operatorIndex;
    char newLastOperator = lastOperator;
    if (operator == '=') {
      newOperator = c;
      newResult.append(c);
      newOperatorIndex = newResult.length();
      newLastOperator = c;
    } else if (isOperator(result.charAt(result.length() - 1))) {
      newOperand1 = Integer.parseInt(result.substring(0, result.length() - 1));
      newOperator = c;
      newResult = new StringBuilder(result.substring(0, result.length() - 1)).append(c);
      newOperatorIndex = newResult.length();
      newLastOperator = c;
    } else if (newOperatorIndex != -1) {
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
    return new SmartCalculator(newResult, newOperand1, newOperand2,
            newOperator, newOperatorIndex, newLastOperator);
  }
}
