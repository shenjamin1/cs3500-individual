package calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple implementation of the Calculator interface that takes straightforward
 * inputs of 32 bits or fewer and operates on them with +, -, or *.
 */
public class SimpleCalculator implements Calculator {

  private final StringBuilder result;
  private final int operand1;
  private final int operand2;
  private final char operator;
  private final int operatorIndex;

  /**
   * Constructs a SimpleCalculator and initializes the result StringBuilder.
   */
  public SimpleCalculator() {
    this.result = new StringBuilder();
    this.operand1 = 0;
    this.operand2 = 0;
    this.operator = '\0';
    this.operatorIndex = -1;
  }

  private SimpleCalculator(StringBuilder result, int operand1, int operand2,
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

  @Override
  public String getResult() {
    return result.toString();
  }

  private Calculator helpClearInput() {
    return new SimpleCalculator(new StringBuilder(), 0, 0, '\0', -1);
  }

  private Calculator helpEqualInput() {
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

  private Calculator helpDigitInput(char c) throws IllegalArgumentException {
    StringBuilder newResult = new StringBuilder(result).append(c);
    if (operator == '=') {
      newResult = new StringBuilder();
      newResult.append(c);
    }
    int newOperand1 = operand1;
    int newOperand2 = operand2;
    if (operatorIndex == -1) {
      try {
        newOperand1 = Integer.parseInt(newResult.toString());
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Illegal operand.");
      }
      if (Integer.toString(newOperand1).length() > 10) {
        throw new IllegalArgumentException("Operand overflow.");
      }
    } else {
      try {
        newOperand2 = Integer.parseInt(newResult.substring(operatorIndex));
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Illegal operand.");
      }
      if (Integer.toString(newOperand2).length() > 10) {
        throw new IllegalArgumentException("Operand overflow.");
      }
    }
    return new SimpleCalculator(newResult, newOperand1, newOperand2, operator, operatorIndex);
  }

  private Calculator helpOperatorInput(char c) {
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
      newOperand1 = Integer.parseInt(result.substring(0));
      newResult.append(c);
      newOperatorIndex = newResult.length();
    }

    return new SimpleCalculator(newResult, newOperand1, newOperand2, newOperator, newOperatorIndex);
  }

  private boolean validInput(char c) {
    List<Character> validInputs = new ArrayList<>(
            List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '*', '=', 'C')
    );
    return validInputs.contains(c);
  }

  private boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '*';
  }

  private int calculate(int operand1, int operand2, char operator) {
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
}
