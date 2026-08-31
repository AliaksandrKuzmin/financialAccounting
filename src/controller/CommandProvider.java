package controller;

import controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private final Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider () {
        commands.put(CommandName.ADD_RECORD, new AddRecordCommand());
        commands.put(CommandName.UPDATE_RECORD, new UpdateRecordCommand());
        commands.put(CommandName.FIND_BY_CATEGORY, new FindByCategoryCommand());
        commands.put(CommandName.FIND_BY_DATE, new FindByDateCommand());
        commands.put(CommandName.SHOW_ALL, new ShowAllCommand());
        commands.put(CommandName.SHOW_PERIOD, new ShowPeriodCommand());
        commands.put(CommandName.UNKNOWN, new UnknownCommand());
        commands.put(CommandName.SHOW_BALANCE, new ShowBalanceCommand());
    }

    public Command getCommand(CommandName name) {
        return commands.get(name);
    }
}
