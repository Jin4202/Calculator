package com.example.calculator;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class
CalculationTest {
    /*
    @Test
    public void solve_isCorrect() {
        //check simple arithmetic
        assertEquals(Double.compare(solve_isCorrect("5+5"), 10), 0);
        assertEquals(Double.compare(solve_isCorrect("15-5"), 10), 0);
        assertEquals(Double.compare(solve_isCorrect("2x5"), 10), 0);
        assertEquals(Double.compare(solve_isCorrect("100/10"), 10), 0);

        //check the order of operations
        assertEquals(Double.compare(solve_isCorrect("5+1x5"), 10), 0);
        assertEquals(Double.compare(solve_isCorrect("5x(1+1)"), 10), 0);

        //check auto-parenthesize
        assertEquals(Double.compare(solve_isCorrect("(9+1"), 10), 0);
        assertEquals(Double.compare(solve_isCorrect("5+5)"), 10), 0);

        //check for exceptions
        assertTrue(checkForExceptions("5/0"));
        assertTrue(checkForExceptions("10+"));
        assertTrue(checkForExceptions("+10"));
        assertTrue(checkForExceptions("1++9"));
    }
    */

    @Test
    public void testPlusOperator() {
        assertEquals(0, Double.compare(solve_isCorrect("5+5"), 10));
    }

    @Test
    public void testMinusOperator() {
        assertEquals(0, Double.compare(solve_isCorrect("15-5"), 10));
    }
    @Test
    public void testMultiplyOperator() {
        assertEquals(0, Double.compare(solve_isCorrect("2x5"), 10));
    }
    @Test
    public void testDivideOperator() {
        assertEquals(0, Double.compare(solve_isCorrect("100/10"), 10));
    }
    @Test
    public void testOrderOfOperator() {
        assertEquals(0, Double.compare(solve_isCorrect("5+1x5"), 10));
    }
    @Test
    public void testParenthesis() {
        assertEquals(0, Double.compare(solve_isCorrect("5x(1+1)"), 10));
    }
    @Test
    public void testAuto_ParenthesizeOpening() {
        assertEquals(0, Double.compare(solve_isCorrect("(9+1"), 10));
    }
    @Test
    public void testAuto_ParenthesizeClosing() {
        assertEquals(0, Double.compare(solve_isCorrect("5+5)"), 10));
    }
    @Test
    public void testZeroDivisionException() {
        assertTrue(checkForExceptions("5/0"));
    }
    @Test
    public void testNumberSkippedAtEndException() {
        assertTrue(checkForExceptions("10+"));
    }
    @Test
    public void testNumberSkippedAtBeginningException() {
        assertTrue(checkForExceptions("+10"));
    }
    @Test
    public void testNumberSkippedInBetweenException() {
        assertTrue(checkForExceptions("1++9"));
    }



    private double solve_isCorrect(String eq) {
        char[] chars = eq.toCharArray();
        ArrayList<Character> arr = new ArrayList<>();
        for(char c : chars) {
            arr.add(c);
        }


        try {
            Calculation calculation = new Calculation(arr);
            ArrayList<Integer> answer = calculation.solveAnswer();
            int numerator = answer.get(0);
            int denominator = answer.get(1);
            return (double) numerator/denominator;
        } catch (InvalidFormatError | OverflowException invalidFormatError) {
            invalidFormatError.printStackTrace();
        }
        return 0;
    }

    private boolean checkForExceptions(String eq) {
        char[] chars = eq.toCharArray();
        ArrayList<Character> arr = new ArrayList<>();
        for(char c : chars) {
            arr.add(c);
        }

        try {
            Calculation calculation = new Calculation(arr);
            calculation.solveAnswer();
        } catch (InvalidFormatError | ArithmeticException | OverflowException e) {
            return true;
        }
        return false;
    }

}