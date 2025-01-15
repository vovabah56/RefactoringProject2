package org.example;


public class RoomManager {
    private Room room;

    public RoomManager(Room room) {
        this.room = room;
    }

    public String getDescription() {
        return room.getDescription();
    }


    public Room getRoom() {
        return room;
    }

    public void setExits(Room north, Room east, Room south, Room west) {
        room.setExits(north, east, south, west);
    }

    public Room getNorthExit() {
        return room.northExit;
    }

    public Room getEastExit() {
        return room.eastExit;
    }

    public Room getSouthExit() {
        return room.southExit;
    }

    public Room getWestExit() {
        return room.westExit;
    }
}
