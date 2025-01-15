package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ErrorGuessingTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUp() {
        // Перенаправляем вывод в outputStream
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(outputStream));
    }

    @Test
    public void testEmptyCommand() {
        // Эмулируем пустую команду и выход
        String userInput = "\nquit\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        Game game = new Game();
        game.play();

        // Проверка, что вывод содержит сообщение об ошибке для пустой команды
        String output = outputStream.toString();
        assertTrue(output.contains("I don't know what you mean..."),
                "Expected an error message for empty command");
    }

    @Test
    public void testNullCommand() {
        // Эмулируем ввод null (несуществующей) команды и выход
        String userInput = "null\nquit\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        Game game = new Game();
        game.play();

        // Проверка, что вывод содержит сообщение об ошибке для некорректной команды
        String output = outputStream.toString();
        assertTrue(output.contains("I don't know what you mean..."),
                "Expected an error message for null command");
    }
}