package controller.impl;

import controller.Command;
import logic.RecordLogic;
import logic.LogicException;

import java.util.Map;

public class ShowBalanceCommand implements Command {

    private final RecordLogic logic;

    public ShowBalanceCommand(RecordLogic logic) {
        this.logic = logic;
    }

    @Override
    public String execute(Map<String, String> params) {
        try {
            double balance = logic.showBalance();
            return "Текущий баланс: " + balance;

        } catch (LogicException e) {
            return "Ошибка логики: " + e.getMessage();

        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }
}

