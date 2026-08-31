package controller.impl;

import controller.Command;
import logic.Logic;
import logic.LogicException;
import logic.LogicProvider;

public class ShowPeriodCommand implements Command {

    private final Logic logic = LogicProvider.getInstance();

    @Override
    public String execute(String request) {
        try {
            return logic.showPeriod(request);
        } catch (LogicException e) {
            return "Ошибка при выводе записей за период: " + e.getMessage();
        }
    }
}
