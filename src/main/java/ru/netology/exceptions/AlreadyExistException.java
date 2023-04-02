package ru.netology.exceptions;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException (String msg) {super("Game" + msg + "already installed");}
}
