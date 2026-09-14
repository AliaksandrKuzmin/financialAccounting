package controller.impl;

import controller.Command;
import logic.RecordLogic;
import logic.LogicException;

import java.util.Map;

public class DeleteRecordCommand implements Command {

    private final RecordLogic logic;

    public DeleteRecordCommand(RecordLogic logic) {
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

            logic.deleteRecord(id);
            return "Запись удалена: ID = " + id;

        } catch (LogicException e) {
            return "Ошибка логики: " + e.getMessage();

        } catch (NumberFormatException e) {
            return "Ошибка: ID должен быть числом.";

        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }
}
