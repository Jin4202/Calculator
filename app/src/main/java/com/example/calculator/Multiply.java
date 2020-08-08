package com.example.calculator;

public class Multiply extends PrimaryOperators {
    public Multiply () {
        super('x');
    }

    public NumberToken calculate(Token num1, Token num2) {
        return new NumberToken(Math.multiplyExact(num1.getmNumerator(), num2.getmNumerator()), Math.multiplyExact(num1.getmDenominator(), num2.getmDenominator()));
    }
}
