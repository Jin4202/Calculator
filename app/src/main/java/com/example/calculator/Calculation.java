package com.example.calculator;

import java.util.ArrayList;

public class Calculation {

    private ArrayList<Token> mStringArrayList;

    public Calculation(ArrayList<Character> arr) {
        mStringArrayList = setTokenArrayList(arr);
        pairParenthesis();
    }

    private void pairParenthesis() {
        int opening = 0;
        int closing = 0;
        for(Token t : mStringArrayList) {
            if(t instanceof Parenthesis) {
                if(t.getOperator() == '(') {
                    opening++;
                } else {
                    closing++;
                }
            }
        }

        if(opening != closing) {
            int addParenthesis = opening - closing;
            int repeat = Math.abs(addParenthesis);
            Parenthesis p;
            if(addParenthesis > 0) {
                p = new Parenthesis(')');
            } else {
                p = new Parenthesis('(');
            }

            for(int i = 0; i < repeat; i++) {
                mStringArrayList.add(p);
            }
        }
    }

    public double solveAnswer() throws InvalidFormatError{
        ArrayList<Token> tokenArr = mStringArrayList;
        ArrayList<Token> answer = solveAnswer(tokenArr);
        if(answer.isEmpty()) {
            throw new InvalidFormatError();
        }
        return solveAnswer(tokenArr).get(0).getNumber();
    }
    private ArrayList<Token> solveAnswer(ArrayList<Token> tokenArr) throws InvalidFormatError{
        while(isParenthesisExist(tokenArr)) {
            ArrayList<ArrayList<Token>> arrays = splitByParenthesis(tokenArr);
            ArrayList<Token> solvedArray = solveAnswer(arrays.get(1));
            tokenArr = new ArrayList<>();
            tokenArr.addAll(arrays.get(0));
            tokenArr.addAll(solvedArray);
            tokenArr.addAll(arrays.get(2));
        }
        solve(tokenArr, PrimaryOperators.class);

        solve(tokenArr, SecondaryOperators.class);
        return tokenArr;
    }

    //Print stringArrayList (for debug/testing)
    private void printStrArr(ArrayList<String> strArr) {
        System.out.print("::::::::");
        for(String s : strArr) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    private boolean isParenthesisExist(ArrayList<Token> tokenArr) {
        for(Token t : tokenArr) {
            if(t instanceof Parenthesis) {
                return true;
            }
        }
        return false;
    }

    private void solve(ArrayList<Token> tokenArr, Class<?> c) throws InvalidFormatError {
        for(int i = 0; i < tokenArr.size(); i++) {
            Token current = tokenArr.get(i);
            if(c.isInstance(current)) {
                OperatorToken operatorTokenToken = (OperatorToken) current;

                if(i+1 >= tokenArr.size() ||                    //when a number does not exist after the operator
                   i == 0 ||                                    //when a number does not exist before the operator
                   tokenArr.get(i+1) instanceof OperatorToken)  // when a number does not exist between the operators
                {
                    throw new InvalidFormatError();
                }

                Token num1 = tokenArr.get(i-1);
                Token num2 = tokenArr.get(i+1);
                NumberToken solvedToken = operatorTokenToken.calculate(num1, num2);
                tokenArr.set(i-1, solvedToken);
                tokenArr.remove(i);
                tokenArr.remove(i);
                i--;
            }
        }
    }

    private ArrayList<Token> setTokenArrayList(ArrayList<Character> charArr) {
        ArrayList<Token> tokenArr = new ArrayList<>();
        StringBuffer number = new StringBuffer();
        for(char c : charArr) {
            if(Character.isDigit(c) || c == '.') {
                number.append(c);
            } else {
                if(!number.toString().equals("")) {
                    tokenArr.add(new NumberToken(Double.parseDouble(number.toString())));
                }
                if(c == 'x') {
                    tokenArr.add(new Multiply());
                } else if(c == '/') {
                    tokenArr.add(new Divide());
                } else if(c == '+') {
                    tokenArr.add(new Plus());
                } else if(c == '-') {
                    tokenArr.add(new Minus());
                } else {
                    tokenArr.add(new Parenthesis(c));
                }
                number = new StringBuffer();
            }
        }

        if(!number.toString().equals("")) {
            tokenArr.add(new NumberToken(Double.parseDouble(number.toString())));
        }
        return tokenArr;
    }

    private ArrayList<ArrayList<Token>> splitByParenthesis(ArrayList<Token> arr) throws InvalidFormatError {
        ArrayList<ArrayList<Integer>> indexOfPs = findingParenthesis(arr);
        int fromIndex = indexOfPs.get(0).get(0);
        int toIndex = indexOfPs.get(1).get(indexOfPs.get(1).size()-1);

        ArrayList<Token> subArray0 = (ArrayList<Token>) arr.subList(0, fromIndex);
        ArrayList<Token> subArray1 = (ArrayList<Token>) arr.subList(fromIndex+1, toIndex);
        ArrayList<Token> subArray2 = (ArrayList<Token>) arr.subList(toIndex+1, arr.size());

        ArrayList<ArrayList<Token>> arrays = new ArrayList<>();
        arrays.add(subArray0);
        arrays.add(subArray1);
        arrays.add(subArray2);
        return arrays;
    }

    //Need change for the exceptions such as [ when the parenthesis are not paired]
    private ArrayList<ArrayList<Integer>> findingParenthesis(ArrayList<Token> arr) throws InvalidFormatError {
        ArrayList<Integer> openingStack = new ArrayList<>();
        ArrayList<Integer> closingStack = new ArrayList<>();

        for(int i = 0; i < arr.size(); i++) {
            Token token = arr.get(i);
            char c = token.getOperator();
            if (c == '(') {
                openingStack.add(i);
            } else if (c == ')') {
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
