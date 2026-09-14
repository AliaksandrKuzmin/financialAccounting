package controller.impl;

import controller.Command;

import java.util.Map;

public class UnknownCommand implements Command {

    @Override
    public String execute(Map<String, String> params) {
        return "Ошибка: неизвестная команда.";
    }
}
