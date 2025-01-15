package org.example;

public class HelpCommand implements CommandAction {
    private GameMediator mediator;

    public HelpCommand(GameMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void execute(Command command) {
        System.out.println("Available commands: go, help, quit");
    }
}
