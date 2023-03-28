package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameStoreTest {
    GameStore store = new GameStore();

    @Test
    public void shouldAddGame() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddGameAlreadyExistException() {
//        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
//
//        Assertions.assertThrows(AlreadyExistException.class ()->{
//            Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
//        });
    }

    @Test
    void addPlayTime() {
        store.addPlayTime("Player1", 100);
        store.addPlayTime("Player1", 200);
        store.addPlayTime("Player1", 300);

//        assertEquals(100 + 200 + 300, store.getTimePLayed("Player1"));
    }

    @Test
    void getMostPlayer() {
        store.addPlayTime("Player1", 100);
        store.addPlayTime("Player2", 200);
        store.addPlayTime("Player3", 300);

        Assertions.assertEquals("Player3", store.getMostPlayer());
    }

    @Test
    void getSumPlayedTime() {
        store.addPlayTime("Player1", 100);
        store.addPlayTime("Player2", 200);
        store.addPlayTime("Player3", 300);
        Assertions.assertEquals(300 + 200 + 100, store.getSumPlayedTime());
    }
}
