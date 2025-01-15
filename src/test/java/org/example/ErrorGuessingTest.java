package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class ErrorGuessingTest {

    private Game game;
    private Parser parserMock;

    @BeforeEach
    public void setUp() {
        game = new Game();
        parserMock = mock(Parser.class);
        game.parser = parserMock;
    }

    @Test
    public void testEmptyCommand() {
        Command emptyCommand = new Command("", null);
        when(parserMock.getCommand()).thenReturn(emptyCommand).thenReturn(new Command("quit", null));

        game.play();

        // Ожидаем, что неизвестная команда не вызовет ошибок
        verify(parserMock, times(2)).getCommand();
    }

    @Test
    public void testNullCommand() {
        Command nullCommand = new Command(null, null);
        when(parserMock.getCommand()).thenReturn(nullCommand).thenReturn(new Command("quit", null));

        game.play();

        // Ожидаем, что игра корректно обработает null-команду
        verify(parserMock, times(2)).getCommand();
    }
}

