package controller.impl;

import controller.Command;
import logic.RecordLogic;
import logic.LogicException;

import java.util.Map;

public class FindByCategoryCommand implements Command {

    private final RecordLogic logic;

    public FindByCategoryCommand(RecordLogic logic) {
        this.logic = logic;
    }

    @Override
    public String execute(Map<String, String> params) {
        try {
            String category = params.get("category");

            if (category == null || category.isBlank()) {
                return "Ошибка: параметр 'category' отсутствует.";
            }

            var records = logic.findByCategory(category);

            if (records.isEmpty()) {
                return "Записей с категорией '" + category + "' не найдено.";
            }

            StringBuilder sb = new StringBuilder("Найденные записи:\n");
            for (var r : records) {
                sb.append(r).append("\n");
            }

            return sb.toString();

        } catch (LogicException e) {
            return "Ошибка логики: " + e.getMessage();

        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }
}

