package controller.impl;

import controller.Command;
import logic.Logic;
import logic.LogicException;
import logic.LogicProvider;

public class UpdateRecordCommand implements Command {

    private final Logic logic = LogicProvider.getInstance();

    @Override
    public String execute(String request) {
        try {
            return logic.updateRecord(request);
        } catch (LogicException e) {
            return "Ошибка при обновлении записи: " + e.getMessage();
        }
    }
}

