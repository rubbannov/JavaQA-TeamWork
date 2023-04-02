package ru.netology.exceptions;

public class HoursMustBePositiveGamesStoreException extends RuntimeException{
    public HoursMustBePositiveGamesStoreException(int msg) {
        super(
                "Hours cold not be negative or zero, current hours: " + msg);
    }
}
