package org.example;

public class QuitCommand implements CommandAction {
    private GameMediator mediator;

    public QuitCommand(GameMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void execute(Command command) {
        System.out.println("Thank you for playing. Goodbye.");
        mediator.quitGame();
    }
}
