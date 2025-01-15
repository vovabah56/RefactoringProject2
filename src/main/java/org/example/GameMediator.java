package org.example;

public class GameMediator {



    public void movePlayerToRoom(String direction, Game game) {
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

