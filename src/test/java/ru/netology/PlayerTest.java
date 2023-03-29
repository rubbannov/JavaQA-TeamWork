package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashSet;
import java.util.Set;

public class PlayerTest {
    GameStore store = new GameStore();
    Player player = new Player("Nagibator777");

    Game game1 = store.publishGame("PUBG", "Battle Royal");
    Game game2 = store.publishGame("CS GO", "Шутер");
    Game game3 = store.publishGame("RDR 2", "Action-adventure");
    Game game4 = store.publishGame("Witcher 3", "RPG");
    Game game5 = store.publishGame("TES V: Skyirm", "RPG");
    Game game6 = store.publishGame("CyberPunk 2077", "Action-adventure");


    @BeforeEach
    public void setUp() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);
    }

    @Test
    void allGamesTest() {
        Set<Game> expected = new HashSet<>();
        expected.add(game1);
        expected.add(game2);
        expected.add(game3);
        expected.add(game4);
        expected.add(game5);

        assertEquals(expected, player.allGames());
    }

    //    @Test
//    public void testInstallGame() {
//        Game[] expected = {game1, game2, game3, game4, game5};
//        Game[] actual =
//    }
    @Test
    public void testPlayGame() {

        int expected = 3;
        int actual = player.play(game1, 3);
        assertEquals(expected,actual);
    }
    @Test
    public void testUninstalledPlayGame() {
        assertThrows(RuntimeException.class, () -> {
            player.play(game6, 2);
        });
    }
    @Test
    public void testNegativeHoursPlayGame() {

        assertThrows(RuntimeException.class, () -> {
        player.play(game2, -3);
        });
    }
    @Test
    public void testAmountHoursPlayGame() {

        int expected = 5;
        int actual = player.play(game3, 3) + player.play(game4,2);
        assertEquals(expected,actual);
    }

    @Test
    public void testSumGenreIfOneGame() {
        player.play(game1, 3);

        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }
    @Test
    public void testSumGenreIfFewGames() {
        player.play(game4,3);
        player.play(game5, 2);
        player.play(game3, 3);

        int expected = 5;
        int actual = player.sumGenre("RPG");
        assertEquals(expected, actual);
    }
    @Test
    public void testMostPlayerByGenre() {
        player.play(game4,10);
        player.play(game5, 6);

        Game[] expected = {game4};
        Game[] actual = new Game[]{player.mostPlayerByGenre("RPG")};

        assertArrayEquals(expected, actual);
    }
    @Test
    public void testMostPlayerByGenreIfDoesNotPlay() {   //Мне пришлось исправить тест, чтобы разобраться.
                                                         // метод возвращает не массив Game[] а объкет Game.
                                                         // (Можно было бы сделать assertNull)
        player.play(game4,10);
        player.play(game5, 6);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Шутер");

        assertEquals(expected, actual);
    }

}
