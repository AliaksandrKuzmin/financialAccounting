package controller.impl;

import controller.Command;
import logic.RecordLogic;
import logic.LogicException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;


public class FindByDateCommand implements Command {

    private final RecordLogic logic;

    public FindByDateCommand(RecordLogic logic) {
        this.logic = logic;
    }

    @Override
    public String execute(Map<String, String> params) {
        try {
            String dateStr = params.get("date");

            if (dateStr == null || dateStr.isBlank()) {
                return "Ошибка: параметр 'date' отсутствует.";
            }

            LocalDate date = LocalDate.parse(dateStr);

            var records = logic.findByDate(date);

            if (records.isEmpty()) {
                return "Записей за дату " + dateStr + " нет.";
            }

            StringBuilder sb = new StringBuilder("Записи за дату " + dateStr + ":\n");
            for (var r : records) {
                sb.append(r).append("\n");
            }

            return sb.toString();

        } catch (DateTimeParseException e) {
            return "Ошибка: неверный формат даты. Используйте YYYY-MM-DD.";

        } catch (LogicException e) {
            return "Ошибка логики: " + e.getMessage();

        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }
}

