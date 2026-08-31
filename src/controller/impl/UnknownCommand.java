package controller.impl;

import controller.Command;

public class UnknownCommand implements Command {

    @Override
    public String execute(String request) {
        return "Неизвестная команда. Проверьте имя команды.";
    }
}
