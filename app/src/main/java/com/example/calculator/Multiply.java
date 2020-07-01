package com.example.calculator;

public class Multiply extends PrimaryOperators {
    public Multiply () {
        super('x');
    }

    public NumberToken calculate(Token num1, Token num2) {
        return new NumberToken(num1.getNumber()*num2.getNumber());
    }
}
