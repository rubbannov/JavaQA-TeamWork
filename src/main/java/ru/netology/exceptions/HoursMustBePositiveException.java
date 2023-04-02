package ru.netology.exceptions;

public class HoursMustBePositiveException extends RuntimeException{
    public HoursMustBePositiveException(int msg) {
        super(
                "Hours cold not be negative or zero, current hours: " + msg);
    }
}
