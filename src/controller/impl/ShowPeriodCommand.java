package controller.impl;

import controller.Command;
import logic.RecordLogic;
import logic.LogicException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class ShowPeriodCommand implements Command {

    private final RecordLogic logic;

    public ShowPeriodCommand(RecordLogic logic) {
        this.logic = logic;
    }

    @Override
    public String execute(Map<String, String> params) {
        try {
            String fromStr = params.get("from");
            String toStr = params.get("to");

            if (fromStr == null || fromStr.isBlank()) {
                return "Ошибка: параметр 'from' отсутствует.";
            }

            if (toStr == null || toStr.isBlank()) {
                return "Ошибка: параметр 'to' отсутствует.";
            }

            LocalDate from = LocalDate.parse(fromStr);
            LocalDate to = LocalDate.parse(toStr);

            var records = logic.findByPeriod(from, to);

            if (records.isEmpty()) {
                return "Записей за период нет.";
            }

            StringBuilder sb = new StringBuilder("Записи за период " + fromStr + " — " + toStr + ":\n");
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
