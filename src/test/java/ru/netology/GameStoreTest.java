package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.exceptions.AlreadyExistException;
import ru.netology.exceptions.PlayerNotRegisteredExeption;

public class GameStoreTest {
    GameStore store = new GameStore();

    @Test
    public void shouldAddGame() { //Добавление игры
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game)); // и поиск игры в списке
    }

    @Test
    void containsGameNoGameTest() { //Поиск несуществующей игры
        Game game = new Game("GameName", "GameGenre", store);
        assertFalse(store.containsGame(game));
    }

    @Test
    public void shouldAddGameAlreadyExistException() { //Добавление в список уже существующей игры
        store.publishGame("Нетология Баттл Онлайн", "Аркады");


        Assertions.assertThrows(AlreadyExistException.class, () -> {
            store.publishGame("Нетология Баттл Онлайн", "Аркады");
        });
    }

    @Test
    void addPlayTime() { // Сумма нескольких записей часов
        store.addPlayTime("Player1", 100);
        store.addPlayTime("Player1", 200);
        store.addPlayTime("Player1", 300);

        assertEquals(100 + 200 + 300, store.getTimePlayer("Player1"));
    }

    @Test
    void getTimePlayerNoPlayer() {
        store.addPlayTime("Player1", 100);
        store.addPlayTime("Player2", 200);
        store.addPlayTime("Player3", 300);
        assertThrows(PlayerNotRegisteredExeption.class, () -> {
            store.getTimePlayer("NoSuchPlayerName");
        });
    }


    @Test
    void addPlayTimeBelowZero() {        //Запись отрицательного количества часов
        Assertions.assertThrows(RuntimeException.class, () -> {
            store.addPlayTime("PLayer1", -100);
        });
    }

    @Test
    void getMostPlayer() { //Поиск игрока сыгравшего больше всего времени
        store.addPlayTime("Player1", 100);
        store.addPlayTime("Player2", 200);
        store.addPlayTime("Player3", 300);

        Assertions.assertEquals("Player3", store.getMostPlayer());
    }


    @Test
    void getMostPlayerNull() { //Поиск игрока сыгравшего больше всего времени, когда список пуст
        assertNull(store.getMostPlayer());
    }

    @Test
    void getSumPlayedTime() { // Сумма времени всех игроков
        store.addPlayTime("Player1", 100);
        store.addPlayTime("Player2", 200);
        store.addPlayTime("Player3", 300);
        Assertions.assertEquals(300 + 200 + 100, store.getSumPlayedTime());
    }

    @Test
    void getSumPlayedTimeEmptyStore() { // Сумма всех игроков для пустого каталога
        Assertions.assertEquals(0, store.getSumPlayedTime());
    }


}
