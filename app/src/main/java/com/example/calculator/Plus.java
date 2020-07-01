package com.example.calculator;

public class Plus extends SecondaryOperators {
    public Plus() {
        super('+');
    }

    public NumberToken calculate(Token num1, Token num2) {
        return new NumberToken(num1.getNumber()+num2.getNumber());
    }
}
