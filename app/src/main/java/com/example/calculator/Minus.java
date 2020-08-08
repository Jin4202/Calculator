package com.example.calculator;

public class Minus extends SecondaryOperators {
    public Minus() {
        super('-');
    }

    public NumberToken calculate(Token num1, Token num2) {
        int newNumerator = Math.subtractExact(Math.multiplyExact(num1.getmNumerator(), num2.getmDenominator()), Math.multiplyExact(num2.getmNumerator(), num1.getmDenominator()));
        int newDenominator = Math.multiplyExact(num1.getmDenominator(), num2.getmDenominator());
        return new NumberToken(newNumerator, newDenominator);
    }
}
