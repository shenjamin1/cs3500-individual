package calculator;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractCalculator implements Calculator {
  protected final StringBuilder result;
  protected final int operand1;
  protected final int operand2;
  protected final char operator;
  protected final int operatorIndex;

  protected AbstractCalculator() {
    this.result = new StringBuilder();
    this.operand1 = 0;
    this.operand2 = 0;
    this.operator = '\0';
    this.operatorIndex = -1;
  }

  protected AbstractCalculator(StringBuilder result, int operand1, int operand2,
                               char operator, int operatorIndex) {
    this.result = result;
    this.operand1 = operand1;
    this.operand2 = operand2;
    this.operator = operator;
    this.operatorIndex = operatorIndex;
  }

  @Override
  public Calculator input(char c) throws IllegalArgumentException {
    if (!validInput(c)) {
      throw new IllegalArgumentException("Not a valid input.");
    }
    if (c == 'C') {
      return helpClearInput();
    } else if (c == '=') {
      return helpEqualInput();
    } else if (Character.isDigit(c)) {
      return helpDigitInput(c);
    } else if (isOperator(c)) {
      return helpOperatorInput(c);
    }
    return this;
  }

  protected abstract Calculator helpClearInput();

  protected abstract Calculator helpEqualInput();

  protected abstract Calculator helpDigitInput(char c);

  protected abstract Calculator helpOperatorInput(char c);

  @Override
  public String getResult() {
    return result.toString();
  }

  protected boolean validInput(char c) {
    List<Character> validInputs = new ArrayList<>(List.of(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '*', '=', 'C'));
    return validInputs.contains(c);
  }

  protected int calculate(int operand1, int operand2, char operator) {
    long solution = 0;
    switch (operator) {
      case '+':
        solution = (long) operand1 + operand2;
        break;
      case '-':
        solution = operand1 - operand2;
        break;
      case '*':
        solution = (long) operand1 * operand2;
        break;
      default:
        break;
    }
    if (solution < Integer.MIN_VALUE || solution > Integer.MAX_VALUE) {
      solution = 0;
    }
    return (int) solution;
  }

  protected boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '*';
  }
}
