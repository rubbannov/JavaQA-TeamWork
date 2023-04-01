package ru.netology;

import ru.netology.exceptions.AlreadyExistException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStore {
    private List<Game> games = new ArrayList<>();

    /**
     * Информация о том, какой игрок сколько играл в игры этого каталога
     * Ключ - имя игрока
     * Значение - суммарное количество часов в игры этого каталога
     */
    private Map<String, Integer> playedTime = new HashMap<>();

    /**
     * Создание объекта игры с заданными заголовком и жанром
     * Каждый объект игры помнит объект каталога, которому она принадлежит
     */
    public Game publishGame(String title, String genre) {
        Game game = new Game(title, genre, this);
        if (!games.contains(game)) {
            games.add(game);
            return game;
        } else {
            throw new AlreadyExistException(
                    "Game" + game.getTitle() + "already published"
            );
        }
    }

    /**
     * Проверяет наличие игры в каталоге и возвращает true
     * если игра есть и false иначе
     */
    public boolean containsGame(Game game) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).equals(game)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Регистрирует количество времени, которое проиграл игрок
     * за игрой этого каталога. Игрок задаётся по имени. Время должно
     * суммироваться с прошлым значением для этого игрока
     */
    public void addPlayTime(String playerName, int hours) {
        if (hours < 0) {
            return;
        } else if (playedTime.containsKey(playerName)) {
            playedTime.put(playerName, playedTime.get(playerName) + hours);
        } else {
            playedTime.put(playerName, hours);
        }
    }

    public int getTimePlayer(String playerName) {
        if (playedTime.containsKey(playerName)) {
            return playedTime.get(playerName);
        } else {
            return Integer.parseInt(null);
        }
    }

    /**
     * Ищет имя игрока, который играл в игры этого каталога больше всего
     * времени. Если игроков нет, то возвращется null
     */
    public String getMostPlayer() {
        int mostTime = 1;
        String bestPlayer = null;
        for (String playerName : playedTime.keySet()) {
            int playerTime = playedTime.get(playerName);
            if (playerTime > mostTime) {
                mostTime = playerTime;
                bestPlayer = playerName;
            }
        }
        return bestPlayer;
    }

    /**
     * Суммирует общее количество времени всех игроков, проведённого
     * за играми этого каталога
     */
    public int getSumPlayedTime() {
        int sum = 0;
        for (Map.Entry<String, Integer> entry : playedTime.entrySet()) {
            sum = sum + entry.getValue();
        }
        return sum;
    }
}
