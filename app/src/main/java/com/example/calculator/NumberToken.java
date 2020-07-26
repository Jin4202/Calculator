package com.example.calculator;

import java.text.DecimalFormat;
import java.util.HashSet;

public class NumberToken extends Token {
    private int mNumerator;
    private int mDenominator;

    public NumberToken(int numerator, int denominator) {
        super();
        mNumerator = numerator;
        mDenominator = denominator;
        reduction();
    }

    public NumberToken(double number) throws OverflowException {
        super();
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(10);
        mDenominator = (int) Math.pow(10, getDecimalLength(df.format(number)));
        mNumerator = (int) (number * mDenominator);
        reduction();
    }
    private int getDecimalLength(String s) throws OverflowException {
        int dotPos = s.indexOf('.');
        if(dotPos == -1) {
            dotPos = s.length()-1;
        }
        int decimalLength = s.length()-1 - dotPos;
        if(decimalLength >= 10) {
            throw new OverflowException();
        }
        return decimalLength;
    }

    private void isOverflowed() {

    }


    @Override
    public char getOperator() {
        return '\u0000';
    }

    public int getmNumerator() {
        return mNumerator;
    }

    public int getmDenominator() {
        return  mDenominator;
    }

    private void reduction() {
        for(int i : getFactors(mDenominator)) {
            while(mNumerator%i == 0 && mDenominator%i == 0) {
                mNumerator /= i;
                mDenominator /= i;
            }
        }
    }

    private HashSet<Integer> getFactors(int num) {
        int factor = 2;
        HashSet<Integer> factorList = new HashSet<>();
        while(factor <= num) {
            if(num%factor == 0) {
                factorList.add(factor);
                num /= factor;
            } else {
                factor++;
            }
        }
        return new HashSet<>(factorList);
    }




}
