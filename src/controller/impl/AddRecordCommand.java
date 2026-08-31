package controller.impl;

import controller.Command;
import logic.Logic;
import logic.LogicException;
import logic.LogicProvider;

public class AddRecordCommand implements Command {

    private final Logic logic = LogicProvider.getInstance();

    @Override
    public String execute(String request) {
        try {
            return logic.addRecord(request);
        } catch (LogicException e) {
            return "Ошибка при добавлении записи: " + e.getMessage();
        }
    }
}

