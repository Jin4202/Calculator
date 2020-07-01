package com.example.calculator;

public class NumberToken extends Token {
    private double number;
    public NumberToken(double number) {
        super('\u0000', number);
        this.number = number;
    }

    public double getNumber() {
        return number;
    }
}
