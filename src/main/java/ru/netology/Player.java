package ru.netology;

import ru.netology.exceptions.AlreadyExistException;
import ru.netology.exceptions.GameNotInstalled;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Player {
    private String name;

    /**
     * информация о том, в какую игру сколько часов было сыграно
     * ключ - игра
     * значение - суммарное количество часов игры в эту игру
     */
    private Map<Game, Integer> playedTime = new HashMap<>();

    public Player(String name) {
        this.name = name;
    }

    //Нет функции вывода списка уже установленных игр для тестов
    //Также нет функции удаления игры

    public String getName() {
        return name;
    }

    /**
     * добавление игры игроку
     * если игра уже была, никаких изменений происходить не должно
     */
    public void installGame(Game game) {
        if (!playedTime.containsKey(game)) {
            playedTime.put(game, 0); //Не дописана функция проверки игры на наличие у игрока
        } else {
            throw new AlreadyExistException(
                    "Game" + game.getTitle() + "already installed"
            );
        }
    }

    /**
     * игрок играет в игру game на протяжении hours часов
     * об этом нужно сообщить объекту-каталогу игр, откуда была установлена игра
     * также надо обновить значения в мапе игрока, добавив проигранное количество часов
     * возвращает суммарное количество часов, проигранное в эту игру.
     * если игра не была установлена, то надо выкидывать RuntimeException
     */
    public int play(Game game, int hours) {
        game.getStore().addPlayTime(name, hours);
        if (playedTime.containsKey(game)) {
            playedTime.put(game, playedTime.get(game) + hours);// playedTime.get(game) + hours
        } else {
            throw new GameNotInstalled(
                    "Game " + game.getTitle() + "was not installed"
            );
        }
        return playedTime.get(game);

    }

    /**
     * Метод принимает жанр игры (одно из полей объекта игры) и
     * суммирует время, проигранное во все игры этого жанра этим игроком
     */
    public int sumGenre(String genre) {//
        int sum = 0;
        for (Game game : playedTime.keySet()) {
            if (game.getGenre().equals(genre)) {
                sum += playedTime.get(game);
            } else {
                sum = 0;
            }
        }
        return sum;
    }

    /**
     * Метод принимает жанр и возвращает игру этого жанра, в которую играли больше всего
     * Если в игры этого жанра не играли, возвращается null
     */
    public Game mostPlayerByGenre(String genre) {//нереализованная функция
        return null;
    }

    public Set<Game> allGames() {
        Set<Game> games = playedTime.keySet();  // todo Убрать лишнее объявление переменной для упрощения кода
//        Game[] gameArray = games.toArray();  //Я сходу не разобрался как вернуть массив, но
        return games;                          // со списком работать уджобне, написал на этот метод тест
    }
}
