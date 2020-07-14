package com.example.calculator;

public class InvalidFormatError extends Exception {
    public InvalidFormatError() {
        super("Invalid format used");
    }

}
