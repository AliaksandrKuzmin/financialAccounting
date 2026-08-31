package controller.impl;

import controller.Command;
import logic.Logic;
import logic.LogicException;
import logic.LogicProvider;

public class FindByCategoryCommand implements Command {

    private final Logic logic = LogicProvider.getInstance();

    @Override
    public String execute(String request) {
        try {
            return logic.findByCategory(request);
        } catch (LogicException e) {
            return "Ошибка поиска по категории: " + e.getMessage();
        }
    }
}

