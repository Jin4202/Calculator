package com.example.calculator;

import java.util.ArrayList;

public class Calculation {

    private ArrayList<String> stringArrayList;

    public Calculation(ArrayList<Character> arr) {
        stringArrayList = setStringArrayList(arr);
    }

    public String solveAnswer() {
        ArrayList<String> strArr = stringArrayList;
        return solveAnswer(strArr).get(0);
    }
    private ArrayList<String> solveAnswer(ArrayList<String> strArr) {
        while(strArr.contains("(")) {
            ArrayList<ArrayList<String>> arrays = splitByParenthesis(strArr);
            ArrayList<String> solvedArray = solveAnswer(arrays.get(1));
            strArr = new ArrayList<>();
            strArr.addAll(arrays.get(0));
            strArr.addAll(solvedArray);
            strArr.addAll(arrays.get(2));
        }

        strArr = solveForMultiply(strArr);
        strArr = solveForDivide(strArr);
        strArr = solveForPlusOrMinus(strArr);

        return strArr;
    }

    //Print stringArrayList (for debug/testing)
    private void printStrArr(ArrayList<String> strArr) {
        System.out.print("::::::::");
        for(String s : strArr) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    private ArrayList<String> solveForMultiply(ArrayList<String> strArr) {
        ArrayList<String> newStrArr = new ArrayList<>();
        for(int i = 0; i < strArr.size(); i++) {
            String s = strArr.get(i);
            if(s.equals("x")) {
                String num1str = strArr.get(i-1);
                String num2str = strArr.get(i+1);
                double num1 = Double.parseDouble(num1str);
                double num2 = Double.parseDouble(num2str);
                double result = num1 * num2;
                String resultStr = Double.toString(result);
                strArr.set(i-1, resultStr);
                strArr.remove(i);
                strArr.remove(i);
                newStrArr.remove(newStrArr.size()-1);
                newStrArr.add(resultStr);
                i--;
            } else {
                newStrArr.add(s);
            }
        }
        return newStrArr;
    }

    //Need change for exceptions such as [ zero division ]
    private ArrayList<String> solveForDivide(ArrayList<String> strArr) {
        ArrayList<String> newStrArr = new ArrayList<>();
        for(int i = 0; i < strArr.size(); i++) {
            String s = strArr.get(i);
            if(s.equals("/")) {
                String num1str = strArr.get(i-1);
                String num2str = strArr.get(i+1);
                double num1 = Double.parseDouble(num1str);
                double num2 = Double.parseDouble(num2str);
                if(Double.compare(num2,0) == 0) {
                    num2 = 1;
                }
                double result = num1 / num2;
                String resultStr = Double.toString(result);
                strArr.set(i-1, resultStr);
                strArr.remove(i);
                strArr.remove(i);
                newStrArr.remove(newStrArr.size()-1);
                newStrArr.add(resultStr);
                i--;
            } else {
                newStrArr.add(s);
            }
        }
        return newStrArr;
    }

    private ArrayList<String> solveForPlusOrMinus(ArrayList<String> strArr) {
        ArrayList<String> newStrArr = new ArrayList<>();
        for(int i = 0; i < strArr.size(); i++) {
            String s = strArr.get(i);
            if(s.equals("+") || s.equals("-")) {
                String num1str = strArr.get(i-1);
                String num2str = strArr.get(i+1);
                double num1 = Double.parseDouble(num1str);
                double num2 = Double.parseDouble(num2str);
                double result;
                if(s.equals("+")) {
                    result = num1 + num2;
                } else {
                    result = num1 - num2;
                }
                String resultStr = Double.toString(result);
                strArr.set(i-1, resultStr);
                strArr.remove(i);
                strArr.remove(i);
                newStrArr.remove(newStrArr.size()-1);
                newStrArr.add(resultStr);
                i--;
            } else {
                newStrArr.add(s);
            }
        }
        return newStrArr;
    }

    private ArrayList<String> setStringArrayList(ArrayList<Character> charArr) {
        ArrayList<String> strArr = new ArrayList<>();
        StringBuffer number = new StringBuffer();
        for(char c : charArr) {
            if(isNumber(c)) {
                number.append(c);
            } else {
                if(!number.toString().equals("")) {
                    strArr.add(number.toString());
                }
                strArr.add(""+c);
                number = new StringBuffer();
            }
        }
        strArr.add(number.toString());
        return strArr;
    }

    private boolean isNumber(char c) {
        return (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c =='.');
    }

    private ArrayList<ArrayList<String>> splitByParenthesis(ArrayList<String> arr) {
        ArrayList<ArrayList<Integer>> IndexOfPs = findingParenthesis(arr);
        int fromIndex = IndexOfPs.get(0).get(0);
        int toIndex = IndexOfPs.get(1).get(IndexOfPs.get(1).size()-1);

        ArrayList<String> subArray0 = subArrayList(arr, 0, fromIndex);
        ArrayList<String> subArray1 = subArrayList(arr, fromIndex+1, toIndex);
        ArrayList<String> subArray2 = subArrayList(arr, toIndex+1, arr.size());
        ArrayList<ArrayList<String>> arrays = new ArrayList<>();
        arrays.add(subArray0);
        arrays.add(subArray1);
        arrays.add(subArray2);
        return arrays;
    }

    private ArrayList<String> subArrayList(ArrayList<String> arr, int fromIndex, int toIndex) {
        ArrayList<String> substractedList = new ArrayList<>();
        for(int i = fromIndex; i < toIndex; i++) {
            substractedList.add(arr.get(i));
        }
        return substractedList;
    }

    //Need change for the exceptions such as [ when the parenthesis are not paired]
    private ArrayList<ArrayList<Integer>> findingParenthesis(ArrayList<String> arr) {
        ArrayList<Integer> openingStack = new ArrayList<>();
        ArrayList<Integer> closingStack = new ArrayList<>();

        for(int i = 0; i < arr.size(); i++) {
            String s = arr.get(i);
            if(s.equals("(")) {
                openingStack.add(i);
            } else if(s.equals(")")) {
                closingStack.add(i);
            }
            if(openingStack.size() != 0 && openingStack.size() == closingStack.size()) {
                break;
            }
        }

        ArrayList<ArrayList<Integer>> arrays = new ArrayList<>();
        arrays.add(openingStack);
        arrays.add(closingStack);
        return arrays;
    }


}
