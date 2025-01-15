package org.example;

import java.util.HashMap;
import java.util.Map;

public class GameMediator {

    private Game game;
    Parser parser;
    private Room currentRoom;
    private Map<String, CommandAction> commands;

    public GameMediator(Game game, Parser parser) {
        this.game = game;
        this.parser = parser;
        this.commands = new HashMap<>();
        initializeCommands();
    }

    private void initializeCommands() {
        commands.put("go", new GoCommand(this));
        commands.put("help", new HelpCommand(this));
        commands.put("quit", new QuitCommand(this));
    }

    public void processCommand(Command command) {
        String commandWord = command.getCommandWord();

        CommandAction action = commands.get(commandWord);
        if (action != null) {
            action.execute(command);
        } else {
            System.out.println("I don't know what you mean...");
        }
    }

    public void goRoom(Command command) {
        System.out.println("Moving to another room...");
        movePlayerToRoom(command.getSecondWord());
    }

    public void printHelp() {
        System.out.println("Available commands: go, help, quit");
    }

    public void quitGame() {
        game.finished = true;
    }



    public void movePlayerToRoom(String direction) {
        Room currentRoom = game.getRoomManager();
        Room nextRoom = null;

        switch (direction) {
            case "north":
                nextRoom = currentRoom.northExit;
                break;
            case "east":
                nextRoom = currentRoom.eastExit;
                break;
            case "south":
                nextRoom = currentRoom.southExit;
                break;
            case "west":
                nextRoom = currentRoom.westExit;
                break;
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            game.setRoomManager(nextRoom);
            System.out.println("You are " + nextRoom.getDescription());
        }
    }
}

