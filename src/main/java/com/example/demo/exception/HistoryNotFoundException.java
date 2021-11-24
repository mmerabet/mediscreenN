package com.example.demo.exception;

public class HistoryNotFoundException extends RuntimeException{
    public HistoryNotFoundException(String message) {
        super(message);
    }
}
