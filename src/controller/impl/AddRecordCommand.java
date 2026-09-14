package controller.impl;

import controller.Command;
import entity.RecordType;
import logic.LogicException;
import logic.RecordLogic;

import java.util.Map;

public class AddRecordCommand implements Command {

    private final RecordLogic logic;

    public AddRecordCommand(RecordLogic logic) {
        this.logic = logic;
    }

    @Override
    public String execute(Map<String, String> params) {
        try {
            String typeStr = params.get("type");
            String category = params.get("category");
            String amountStr = params.get("amount");
            String description = params.get("description");

            if (typeStr == null || typeStr.isBlank()) {
                return "Ошибка: параметр 'type' отсутствует.";
            }

            if (category == null || category.isBlank()) {
                return "Ошибка: параметр 'category' отсутствует.";
            }

            if (amountStr == null || amountStr.isBlank()) {
                return "Ошибка: параметр 'amount' отсутствует.";
            }

            if (description == null || description.isBlank()) {
                return "Ошибка: параметр 'description' отсутствует.";
            }

            RecordType type = RecordType.valueOf(typeStr.toUpperCase());
            double amount = Double.parseDouble(amountStr);

            var record = logic.addRecord(type, category, amount, description);

            return "Запись добавлена: " + record;

        } catch (IllegalArgumentException e) {
            return "Ошибка: тип должен быть INCOME или EXPENSE.";

        } catch (LogicException e) {
            return "Ошибка логики: " + e.getMessage();

        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }
}


