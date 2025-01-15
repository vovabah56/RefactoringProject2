package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class GameTest {

    private Game game;
    private Game gameSpy;

    private Parser parserMock;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        game = new Game();

        parserMock = Mockito.mock(Parser.class);

        game.parser = parserMock;
        System.setOut(new PrintStream(outputStream));
        gameSpy = spy(new Game());
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