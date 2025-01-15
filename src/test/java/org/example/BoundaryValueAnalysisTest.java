package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoundaryValueAnalysisTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        // Перенаправляем стандартный вывод в outputStream для проверки результата
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testEmptyCommandBoundary() {
        // Мокаем пустую команду и команду выхода
        Command emptyCommand = new Command("", null);
        Parser parserMock = mock(Parser.class);
        when(parserMock.getCommand()).thenReturn(emptyCommand, new Command("quit", null));
        String userInput = "\nquit\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);
        Game game = new Game();
        game.parser = parserMock;

        game.play();

        // Проверка, что пустая команда вызвала сообщение об ошибке
        String output = outputStream.toString();
        assertTrue(output.contains("I don't know what you mean..."),
                output);

        // Проверка, что метод getCommand() не вызывался
        verify(parserMock, times(2)).getCommand();
    }

    @Test
    public void testLongCommandBoundary() {
        // Мокаем очень длинную команду
        String longCommand = "go " + "north".repeat(1000);  // Длинный ввод
        Command longGoCommand = new Command("go", longCommand);

        Parser parserMock = mock(Parser.class);
        when(parserMock.getCommand()).thenReturn(longGoCommand, new Command("quit", null));

        Game game = new Game();
        game.parser = parserMock;

        game.play();

        // Проверка, что игра корректно отработала длинную команду
        String output = outputStream.toString();
        assertTrue(output.contains("I don't know what you mean...") || output.contains("You are"),
                "Expected appropriate handling for long command input");

        // Проверка, что метод getCommand() вызывался дважды
        verify(parserMock, times(2)).getCommand();
    }
}
