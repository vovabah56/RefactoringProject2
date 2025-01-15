package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EquivalencePartitioningTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        // Перенаправляем стандартный вывод в outputStream
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testValidCommands() {
        // Эмулируем ввод корректной команды "go north" и "quit"
        String userInput = "go north\nquit\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        Game game = new Game();
        game.play();

        // Проверка, что игра корректно отработала команду "go north"
        String output = outputStream.toString();
        assertTrue(output.contains("You are"), "Expected movement feedback for valid 'go north' command");
    }

    @Test
    public void testInvalidCommands() {
        // Эмулируем ввод некорректной команды "fly" и "quit"
        String userInput = "fly\nquit\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        Game game = new Game();
        game.play();

        // Проверка, что игра корректно отработала некорректную команду
        String output = outputStream.toString();
        assertTrue(output.contains("I don't know what you mean..."),
                "Expected error message for invalid 'fly' command");
    }
}