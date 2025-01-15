package org.example;

public class GoCommand implements CommandAction {
    private GameMediator mediator;

    public GoCommand(GameMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void execute(Command command) {
        System.out.println("Moving to another room...");
        mediator.movePlayerToRoom(command.getSecondWord());
    }
}

