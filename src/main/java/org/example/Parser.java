package org.example;

import java.util.Scanner;


public class Parser 
{
    private CommandWords commands;
    private Scanner reader;


    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand() {

        System.out.print("> ");

        if (!reader.hasNextLine()) {
            return new Command("quit", null); // Автоматический выход, если нет ввода
        }
        String inputLine = reader.nextLine();
        String[] words = inputLine.split(" ");

        String commandWord = words[0];
        String secondWord = words.length > 1 ? words[1] : null;

        if (commands.isCommand(commandWord)) {
            return new Command(commandWord, secondWord);
        } else {
            return new Command(null, secondWord);
        }
    }
}
