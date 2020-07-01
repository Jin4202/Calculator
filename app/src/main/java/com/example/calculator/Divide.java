package com.example.calculator;

public class Divide extends PrimaryOperators {
    public Divide() {
        super('/');
    }

    public NumberToken calculate(Token num1, Token num2) {
        double num2safe = num2.getNumber();
        if(Double.compare(num2safe,0) == 0) {
            num2safe = 1;
        }
        return new NumberToken(num1.getNumber()/num2safe);
    }
}
