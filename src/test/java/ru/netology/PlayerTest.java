package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.exceptions.AlreadyExistException;
import ru.netology.exceptions.GameNotInstalled;
import ru.netology.exceptions.HoursMustBePositiveGamesStoreException;


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

    @Test
    void getNameTest() {
        assertEquals("Nagibator777", player.getName());
    }

    @Test
    void installGameAlreadyExicstException() {
        assertThrows(AlreadyExistException.class, () -> {
            player.installGame(game3);
        });
    }

    @Test
    void allGamesNoGames() {
        Player NoGamesPlayer = new Player("NoGamesPlayer");
        Set<Game> expected = new HashSet<>();
        assertEquals(expected, NoGamesPlayer.allGames());
    }

    @Test
    public void testPlayGame() {

        int expected = 3;
        int actual = player.play(game1, 3);
        assertEquals(expected, actual);
    }

    @Test
    public void testUninstalledPlayGame() {
        assertThrows(RuntimeException.class, () -> {
            player.play(game6, 2);
        });
    }

    @Test
    public void testNegativeHoursPlayGame() {
        assertThrows(HoursMustBePositiveGamesStoreException.class, () -> {
            player.play(game2, -1);
        });
    }
    @Test
    public void testAmountHoursPlayGame() {

        int expected = 5;
        int actual = player.play(game3, 3) + player.play(game4, 2);
        assertEquals(expected, actual);
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
        player.play(game4, 3);
        player.play(game5, 2);
        player.play(game3, 3);

        int expected = 5;
        int actual = player.sumGenre("RPG");
        assertEquals(expected, actual);
    }

    @Test
    public void testSumGenreIfNoSuchGenre() {
        player.play(game4, 3);
        player.play(game5, 2);
        player.play(game3, 3);

        int expected = 0;
        int actual = player.sumGenre("NoGenre");
        assertEquals(expected, actual);
    }

    @Test
    public void testMostPlayerByGenre() {
        player.play(game4, 10);
        player.play(game5, 6);
        assertEquals(game4, player.mostPlayerByGenre("RPG"));
    }

    @Test
    public void testMostPlayerByGenreIfDoesNotPlay() {
        player.play(game4, 10);
        player.play(game5, 6);

        assertNull(player.mostPlayerByGenre("Шутер"));
    }

    @Test
    public void testDeleteGame() {

        player.deleteGame(game3);
        Set<Game> expected = new HashSet<>();
        expected.add(game1);
        expected.add(game2);
        expected.add(game4);
        expected.add(game5);

        assertEquals(expected, player.allGames());
    }

    @Test
    public void testDeleteFewGame() {

        player.deleteGame(game3);
        player.deleteGame(game4);
        Set<Game> expected = new HashSet<>();
        expected.add(game1);
        expected.add(game2);
        expected.add(game5);

        assertEquals(expected, player.allGames());
    }

    @Test
    void deleteGameException() {
        Game game0 = new Game("NoSuchGame", "NoSuchGenre", store);
        assertThrows(GameNotInstalled.class, () -> {
            player.deleteGame(game0);
        });
    }
}
