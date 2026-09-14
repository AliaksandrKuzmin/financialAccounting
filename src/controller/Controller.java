package controller;
import java.util.Map;

public class Controller {

    private final CommandProvider provider;

    public Controller(CommandProvider provider) {
        this.provider = provider;
    }

    public String doAction(String request) {

        if (request == null || request.isBlank()) {
            return "Ошибка: пустой запрос.";
        }

        String[] parts = request.split("\n", 2);
        String commandName = parts[0].trim();
        String paramsBlock = parts.length > 1 ? parts[1] : "";

        Command command = provider.getCommand(commandName);

        Map<String, String> params = RequestParser.parse(paramsBlock);

        return command.execute(params);
    }

}

