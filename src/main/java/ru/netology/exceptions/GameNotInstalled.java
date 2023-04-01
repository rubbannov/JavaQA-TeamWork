package ru.netology.exceptions;

public class GameNotInstalled extends RuntimeException{
    public GameNotInstalled(String msg) {
        super("Game " + msg + "was not installed");
    }
}
