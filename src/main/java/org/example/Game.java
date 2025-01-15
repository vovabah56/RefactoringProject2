package org.example;

public class Game {
    Parser parser;
    private RoomManager roomManager;
    GameMediator mediator;

    /**
     * Создание игры и инициализация карты.
     */
    public Game() {
        createRooms();
        parser = new Parser();
        mediator = new GameMediator();
    }

    /**
     * Создание комнат и установка выходов.
     */
    private void createRooms() {
        Room outside, theater, pub, lab, office;

        // создание комнат
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // установка выходов
        outside.setExits(null, theater, lab, pub);
        theater.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        lab.setExits(outside, office, null, null);
        office.setExits(null, null, null, lab);

        roomManager = new RoomManager(outside);
    }

    /**
     * Основной игровой цикл.
     */
    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Good bye.");
    }

    /**
     * Приветственное сообщение.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " + roomManager.getDescription());
        printExits();
    }

    /**
     * Вывод доступных выходов.
     */
    private void printExits() {
        System.out.print("Exits: ");
        Room currentRoom =  roomManager.getRoom();
        if (currentRoom.northExit != null) System.out.print("north ");
        if (currentRoom.eastExit != null) System.out.print("east ");
        if (currentRoom.southExit != null) System.out.print("south ");
        if (currentRoom.westExit != null) System.out.print("west ");
        System.out.println();
    }

    /**
     * Обработка команд.
     */
    boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "go":
                mediator.movePlayerToRoom(command.getSecondWord(), this);  // Используем посредника
                break;
            case "quit":
                wantToQuit = quit(command);
                break;
            default:
                System.out.println("I don't know what you mean...");
                break;
        }
        return wantToQuit;
    }

    /**
     * Печать справки.
     */
    void printHelp() {
        System.out.println("You are lost. You are alone.");
        System.out.println("Available commands: go, help, quit");
    }

    /**
     * Обработка выхода из игры.
     */
    boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Получить текущую комнату.
     */
    public Room getRoomManager() {
        return roomManager.getRoom();
    }

    /**
     * Установить текущую комнату.
     */
    public void setRoomManager(Room room) {
        roomManager = new RoomManager(room);
        System.out.println("You are " + roomManager.getDescription());
        printExits();
    }
}
