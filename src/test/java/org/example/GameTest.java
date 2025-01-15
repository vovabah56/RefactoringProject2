package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GameTest {

    private Game game;

    private Parser parserMock;

    @BeforeEach
    public void setUp() {
        game = new Game();

            parserMock = Mockito.mock(Parser.class);

        game.parser = parserMock;
    }

    /**
     * Проверка вызова метода movePlayerToRoom при команде "go".
     */
    @Test
    public void testGoCommandCallsMediator() {
        Command goCommand = new Command("go", "north");

        when(parserMock.getCommand()).thenReturn(goCommand).thenReturn(new Command("quit", null));

        game.play();

        // Проверяем, что метод movePlayerToRoom был вызван 2 раза
        verify(parserMock, times(2)).getCommand();
    }

    /**
     * Проверка вызова метода printHelp при команде "help".
     */
    @Test
    public void testHelpCommand() {
        Command helpCommand = new Command("help", null);

        // Возвращаем команду help, затем quit для завершения игры
        when(parserMock.getCommand()).thenReturn(helpCommand).thenReturn(new Command("quit", null));

        Game spyGame = spy(game);

        spyGame.play();

        // Проверяем, что метод printHelp был вызван ровно один раз
        verify(spyGame, times(1)).printHelp();
    }

    /**
     * Проверка вызова метода quit при команде "quit".
     */
    @Test
    public void testQuitCommand() {
        Command quitCommand = new Command("quit", null);

        when(parserMock.getCommand()).thenReturn(quitCommand);

        Game spyGame = spy(game);

        spyGame.play();

        // Проверяем, что метод quit был вызван ровно один раз
        verify(spyGame, times(1)).quit(quitCommand);
    }

    /**
     * Проверка обработки неизвестной команды.
     */
    @Test
    public void testUnknownCommand() {
        Command unknownCommand = new Command(null, null);

        when(parserMock.getCommand()).thenReturn(unknownCommand).thenReturn(new Command("quit", null));

        Game mockGame  = Mockito.mock(Game.class);
        Game spyGame = spy(mockGame);

        spyGame.play();

        // Проверяем, что при неизвестной команде метод mediator не вызвался
        verify(mockGame, never()).printHelp();
    }
}
