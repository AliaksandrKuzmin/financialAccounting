package controller;

public class Controller {
    private final CommandProvider provider = new CommandProvider();

    public String executeTask(String request) {
        String commandName = request.split("\n")[0].trim();
        CommandName name;

        try {
            name = CommandName.valueOf(commandName);
        } catch (IllegalArgumentException e) {
            name = CommandName.UNKNOWN;
        }

        Command command = provider.getCommand(name);
        return command.execute(request);
    }
}
