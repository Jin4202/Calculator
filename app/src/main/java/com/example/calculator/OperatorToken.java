package com.example.calculator;

public abstract class OperatorToken extends Token{
    private char operator;
    public OperatorToken(char operator) {
        super();
        this.operator = operator;
    }

    public char getOperator() {
        return operator;
    }

    public abstract NumberToken calculate(Token num1, Token num2);

    @Override
    public int getmNumerator() {
        return 0;
    }

    @Override
    public int getmDenominator() {
        return 0;
    }
}
