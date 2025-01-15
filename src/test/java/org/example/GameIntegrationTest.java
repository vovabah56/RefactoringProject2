package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GameIntegrationTest {

    @Test
    public void testHelpCommand() {
        // Эмулируем ввод команды "help" и "quit"
        String userInput = "help\nquit\n";
        ByteArrayInputStream input = new ByteArrayInputStream(userInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);

        // Перенаправляем стандартный вывод
        System.setIn(input);
        System.setOut(printStream);

        // Запускаем игру
        Game game = new Game();
        game.play();
        System.out.println();

        String gameOutput = output.toString();
        System.out.println(gameOutput);
        assertTrue(gameOutput.contains(": go, help, quit"), gameOutput);
    }
}