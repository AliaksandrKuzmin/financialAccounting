package controller.impl;

import controller.Command;
import logic.Logic;
import logic.LogicException;
import logic.LogicProvider;

public class ShowAllCommand implements Command {

    private final Logic logic = LogicProvider.getInstance();

    @Override
    public String execute(String request) {
        try {
            return logic.showAll();
        } catch (LogicException e) {
            return "Ошибка при выводе всех записей: " + e.getMessage();
        }
    }
}

