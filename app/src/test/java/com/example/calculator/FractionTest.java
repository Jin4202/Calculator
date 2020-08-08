package com.example.calculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class FractionTest {

    @Test
    public void testReduction() throws OverflowException {
        NumberToken fraction1 = new NumberToken(1, 5);
        assertEquals(1, fraction1.getmNumerator());
        assertEquals(5, fraction1.getmDenominator());

        NumberToken fraction2 = new NumberToken(25, 1);
        assertEquals(25, fraction2.getmNumerator());
        assertEquals(1, fraction2.getmDenominator());

        NumberToken fraction3 = new NumberToken(1, 1);
        assertEquals(1, fraction3.getmNumerator());
        assertEquals(1, fraction3.getmDenominator());

        NumberToken fraction4 = new NumberToken(25);
        assertEquals(25, fraction4.getmNumerator());
        assertEquals(1, fraction4.getmDenominator());

        NumberToken fraction5 = new NumberToken(0.5);
        assertEquals(1, fraction5.getmNumerator());
        assertEquals(2, fraction5.getmDenominator());

        NumberToken fraction6 = new NumberToken(1025, 3500);
        assertEquals(41, fraction6.getmNumerator());
        assertEquals(140, fraction6.getmDenominator());

        NumberToken fraction7 = new NumberToken(1000000);
        assertEquals(1000000, fraction7.getmNumerator());
        assertEquals(1, fraction7.getmDenominator());

        NumberToken fraction8 = new NumberToken(1000.0);
        assertEquals(1000, fraction8.getmNumerator());
        assertEquals(1, fraction8.getmDenominator());

        NumberToken fraction9 = new NumberToken(1000000000.0);
        assertEquals(1000000000, fraction9.getmNumerator());
        assertEquals(1, fraction9.getmDenominator());

    }

    @Test
    public void testExceptions() {
        assertTrue(isOverflowExceptionThrew(0.1111111111));

        //assertTrue(isOverflowExceptionThrew(1000000000));
    }

    private boolean isOverflowExceptionThrew(double num) {
        try {
            NumberToken fraction = new NumberToken(num);
        } catch (OverflowException e) {
            return true;
        }
        return false;
    }


}
