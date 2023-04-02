package ru.netology.exceptions;

public class GameAlreadyExistException extends RuntimeException {
    public GameAlreadyExistException(String msg) {
        super(msg);
    }
}