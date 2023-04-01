package ru.netology.exceptions;

public class PlayerNotRegisteredExeption extends RuntimeException{
    public PlayerNotRegisteredExeption(String playerName) {
        super("Player: " + "ïs not registered");
    }
}
