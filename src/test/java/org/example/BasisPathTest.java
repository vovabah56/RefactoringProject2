package org.example;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class BasisPathTest {

    @Test
    public void testGoNorthPath() {
        Parser parserMock = mock(Parser.class);
        when(parserMock.getCommand()).thenReturn(new Command("go", "north"), new Command("quit", null));

        Game game = new Game();
        game.parser = parserMock;

        game.play();

        verify(parserMock, times(2)).getCommand();
    }

    @Test
    public void testHelpPath() {
        Parser parserMock = mock(Parser.class);
        when(parserMock.getCommand()).thenReturn(new Command("help", null), new Command("quit", null));

        Game game = spy(new Game());
        game.parser = parserMock;

        game.play();

        verify(game, times(1)).printHelp();
    }
}
