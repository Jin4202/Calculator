package com.example.calculator;

import android.widget.Toast;

public class Divide extends PrimaryOperators {
    public Divide() {
        super('/');
    }

    public NumberToken calculate(Token num1, Token num2) {
        int newNumerator = Math.multiplyExact(num1.getmNumerator(), num2.getmDenominator());
        int newDenominator = Math.multiplyExact(num1.getmDenominator(), num2.getmNumerator());
        if(newDenominator == 0) {
            throw new ZeroDivisionException();
        }
        return new NumberToken(newNumerator, newDenominator);
    }
}
