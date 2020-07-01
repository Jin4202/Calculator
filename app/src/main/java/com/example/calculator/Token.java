package com.example.calculator;

public abstract class Token {

    private char operator;
    private double number;

    public Token(char operator, double number) {
        this.operator = operator;
        this.number = number;
    }

    public char getOperator() {
        return operator;
    }

    public double getNumber() {
        return number;
    }

}
