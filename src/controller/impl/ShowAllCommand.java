package controller.impl;

import controller.Command;
import logic.RecordLogic;

import java.util.Map;

public class ShowAllCommand implements Command {

    private final RecordLogic logic;

    public ShowAllCommand(RecordLogic logic) {
        this.logic = logic;
    }

    @Override
    public String execute(Map<String, String> params) {
        try {
            var records = logic.showAll();

            if (records.isEmpty()) {
                return "Записей пока нет.";
            }

            StringBuilder sb = new StringBuilder("Все записи:\n");
            for (var r : records) {
                sb.append(r).append("\n");
            }

            return sb.toString();

        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }
}
