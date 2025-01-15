package org.example;

public class Game {
    Parser parser;
    private Room roomManager;
    GameMediator mediator;
    boolean finished = false;

    /**
     * Создание игры и инициализация карты.
     */
    public Game() {
        createRooms();
        parser = new Parser();
        mediator = new GameMediator(this, parser);
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

        roomManager = outside;
    }

    /**
     * Основной игровой цикл.
     */
    public void play() {
        printWelcome();

        while (!finished) {
            Command command = parser.getCommand();
            mediator.processCommand(command);
        }
    }

    /**
     * Приветственное сообщение.
     */
    void printWelcome() {
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
        Room currentRoom =  roomManager;
        if (currentRoom.northExit != null) System.out.print("north ");
        if (currentRoom.eastExit != null) System.out.print("east ");
        if (currentRoom.southExit != null) System.out.print("south ");
        if (currentRoom.westExit != null) System.out.print("west ");
        System.out.println();
    }

    /**
     * Обработка команд.
     */


    /**
     * Печать справки.
     */
    void printHelp() {
        System.out.println("You are lost. You are alone.");
        System.out.println("Your command words are: go quit help");
    }


    /**
     * Получить текущую комнату.
     */
    public Room getRoomManager() {
        return roomManager;
    }

    /**
     * Установить текущую комнату.
     */
    public void setRoomManager(Room room) {
        roomManager = room;
        System.out.println("You are " + roomManager.getDescription());
        printExits();
    }
}
