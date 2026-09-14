package controller;

import controller.impl.*;
import logic.RecordLogic;
import logic.LogicProvider;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private final Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {

        RecordLogic recordLogic = LogicProvider.getInstance().getLogic();

        commands.put(CommandName.ADD_RECORD, new AddRecordCommand(recordLogic));
        commands.put(CommandName.UPDATE_RECORD, new UpdateRecordCommand(recordLogic));
        commands.put(CommandName.DELETE_RECORD, new DeleteRecordCommand(recordLogic));
        commands.put(CommandName.FIND_BY_CATEGORY, new FindByCategoryCommand(recordLogic));
        commands.put(CommandName.FIND_BY_DATE, new FindByDateCommand(recordLogic));
        commands.put(CommandName.SHOW_PERIOD, new ShowPeriodCommand(recordLogic));
        commands.put(CommandName.SHOW_ALL, new ShowAllCommand(recordLogic));
        commands.put(CommandName.SHOW_BALANCE, new ShowBalanceCommand(recordLogic));
        commands.put(CommandName.WRONG_REQUEST, new UnknownCommand());
    }

    public Command getCommand(String name) {
        CommandName commandName ;
        Command command;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = commands.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = commands.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }

}
