package org.example;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class BoundaryValueAnalysisTest {

    @Test
    public void testEmptyCommandBoundary() {
        Command emptyCommand = new Command("", null);
        Parser parserMock = mock(Parser.class);
        when(parserMock.getCommand()).thenReturn(emptyCommand, new Command("quit", null));

        Game game = new Game();
        game.parser = parserMock;

        game.play();

        verify(parserMock, times(2)).getCommand();
    }

    @Test
    public void testLongCommandBoundary() {
        String longCommand = "go " + "north".repeat(1000);
        Command longGoCommand = new Command("go", longCommand);

        Parser parserMock = mock(Parser.class);
        when(parserMock.getCommand()).thenReturn(longGoCommand, new Command("quit", null));

        Game game = new Game();
        game.parser = parserMock;

        game.play();

        verify(parserMock, times(2)).getCommand();
    }
}
