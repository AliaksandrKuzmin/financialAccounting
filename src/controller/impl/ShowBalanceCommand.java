package controller.impl;

import controller.Command;
import logic.Logic;
import logic.LogicException;
import logic.LogicProvider;

public class ShowBalanceCommand implements Command {

    private final Logic logic = LogicProvider.getInstance();

    @Override
    public String execute(String request) {
        try {
            return logic.showBalance();
        } catch (LogicException e) {
            return "Ошибка при вычислении баланса: " + e.getMessage();
        }
    }
}

