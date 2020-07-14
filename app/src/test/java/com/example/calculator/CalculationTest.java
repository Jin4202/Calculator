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
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


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

    private double solve_isCorrect(String eq) {
        char[] chars = eq.toCharArray();
        ArrayList<Character> arr = new ArrayList<>();
        for(char c : chars) {
            arr.add(c);
        }
        Calculation calculation = new Calculation(arr);
        double answer = 0;
        try {
            answer = calculation.solveAnswer();
        } catch (InvalidFormatError invalidFormatError) {
            invalidFormatError.printStackTrace();
        }
        return answer;
    }

    private boolean checkForExceptions(String eq) {
        char[] chars = eq.toCharArray();
        ArrayList<Character> arr = new ArrayList<>();
        for(char c : chars) {
            arr.add(c);
        }
        Calculation calculation = new Calculation(arr);
        try {
            calculation.solveAnswer();
        } catch (InvalidFormatError | ArithmeticException e) {
            return true;
        }
        return false;
    }

}