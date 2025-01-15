package org.example;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class PartialTesting {

    @Test
    public void testPartialGoCommand() {
        Command goCommand = new Command("go", "north");
        Parser parserMock = mock(Parser.class);
        when(parserMock.getCommand()).thenReturn(goCommand, new Command("quit", null));

        Game game = spy(new Game());
        game.parser = parserMock;

        game.play();

        verify(game, times(1)).processCommand(goCommand);
    }
}
