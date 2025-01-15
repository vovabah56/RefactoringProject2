package org.example;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class EquivalencePartitioningTest {

    @Test
    public void testValidCommands() {
        Parser parserMock = mock(Parser.class);
        when(parserMock.getCommand()).thenReturn(new Command("go", "north"), new Command("quit", null));

        Game game = new Game();
        game.parser = parserMock;

        game.play();

        verify(parserMock, times(2)).getCommand();
    }

    @Test
    public void testInvalidCommands() {
        Parser parserMock = mock(Parser.class);
        when(parserMock.getCommand()).thenReturn(new Command("fly", null), new Command("quit", null));

        Game game = new Game();
        game.parser = parserMock;

        game.play();

        verify(parserMock, times(2)).getCommand();
    }
}
