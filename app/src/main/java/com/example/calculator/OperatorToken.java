package com.example.calculator;

public abstract class OperatorToken extends Token{
    private char operator;
    public OperatorToken(char operator) {
        super(operator, 0);
        this.operator = operator;
    }

    public char getOperator() {
        return operator;
    }

    public abstract NumberToken calculate(Token num1, Token num2);
}
