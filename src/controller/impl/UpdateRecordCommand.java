package controller.impl;

import controller.Command;
import logic.LogicException;
import logic.RecordLogic;

import java.util.Map;

public class UpdateRecordCommand implements Command {

    private final RecordLogic logic;

    public UpdateRecordCommand(RecordLogic logic) {
        this.logic = logic;
    }

    @Override
    public String execute(Map<String, String> params) {
        try {
            String idStr = params.get("id");
            if (idStr == null || idStr.isBlank()) {
                return "Ошибка: параметр 'id' отсутствует.";
            }

            int id = Integer.parseInt(idStr);

            String description = params.get("description");
            String category = params.get("category");
            String amountStr = params.get("amount");

            if (description == null || description.isBlank()) {
                return "Ошибка: параметр 'description' отсутствует.";
            }

            if (category == null || category.isBlank()) {
                return "Ошибка: параметр 'category' отсутствует.";
            }

            if (amountStr == null || amountStr.isBlank()) {
                return "Ошибка: параметр 'amount' отсутствует.";
            }

            double amount = Double.parseDouble(amountStr);

            var updated = logic.updateRecord(id, category, amount, description);

            return "Запись обновлена: " + updated;

        } catch (NumberFormatException e) {
            return "Ошибка: ID и amount должны быть числами.";

        } catch (LogicException e) {
            return "Ошибка логики: " + e.getMessage();

        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }
}

