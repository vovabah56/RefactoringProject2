package org.example;

import org.junit.jupiter.api.Test;


public class DataFlowTest {

    @Test
    public void testRoomInitialization() {
        Room room = new Room("Test Room");
        assert room.getDescription().equals("Test Room");

        room.setExits(null, null, null, null);
        assert room.northExit == null;
        assert room.eastExit == null;
    }
}