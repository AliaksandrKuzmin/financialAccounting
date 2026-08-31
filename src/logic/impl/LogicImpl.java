package logic.impl;

import dao.DaoException;
import dao.DaoProvider;
import dao.FinancialRecordDao;
import entity.FinancialRecord;
import entity.RecordType;
import logic.Logic;
import logic.LogicException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class LogicImpl implements Logic {

    private final FinancialRecordDao dao = DaoProvider.getInstance();

    // ---------------------------------------------------------
    // ADD RECORD
    // ---------------------------------------------------------
    @Override
    public String addRecord(String request) throws LogicException {
        try {
            Map<String, String> params = parse(request);

            RecordType type = RecordType.valueOf(params.get("type"));
            String category = params.get("category");
            double amount = Double.parseDouble(params.get("amount"));
            String description = params.get("description");

            int id = dao.generateId();
            LocalDateTime now = LocalDateTime.now();

            FinancialRecord record = new FinancialRecord(
                    id, type, category, amount, description, now
            );

            dao.add(record);

            return "Запись добавлена: id=" + id;

        } catch (DaoException e) {
            throw new LogicException("Ошибка DAO", e);
        } catch (Exception e) {
            throw new LogicException("Ошибка формата запроса: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------
    // UPDATE RECORD
    // ---------------------------------------------------------
    @Override
    public String updateRecord(String request) throws LogicException {
        try {
            Map<String, String> params = parse(request);

            int id = Integer.parseInt(params.get("id"));
            RecordType type = RecordType.valueOf(params.get("type"));
            String category = params.get("category");
            double amount = Double.parseDouble(params.get("amount"));
            String description = params.get("description");

            FinancialRecord updated = new FinancialRecord(
                    id, type, category, amount, description, LocalDateTime.now()
            );

            dao.update(updated);

            return "Запись обновлена: id=" + id;

        } catch (DaoException e) {
            throw new LogicException("Ошибка DAO", e);
        } catch (Exception e) {
            throw new LogicException("Ошибка формата запроса: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------
    // FIND BY CATEGORY
    // ---------------------------------------------------------
    @Override
    public String findByCategory(String request) throws LogicException {
        try {
            Map<String, String> params = parse(request);
            String category = params.get("category");

            List<FinancialRecord> all = dao.findAll();
            List<FinancialRecord> filtered = new ArrayList<>();

            for (FinancialRecord r : all) {
                if (r.getCategory().equalsIgnoreCase(category)) {
                    filtered.add(r);
                }
            }

            return formatList(filtered);

        } catch (DaoException e) {
            throw new LogicException("Ошибка DAO", e);
        }
    }

    // ---------------------------------------------------------
    // FIND BY DATE
    // ---------------------------------------------------------
    @Override
    public String findByDate(String request) throws LogicException {
        try {
            Map<String, String> params = parse(request);
            LocalDate date = LocalDate.parse(params.get("date"));

            List<FinancialRecord> all = dao.findAll();
            List<FinancialRecord> filtered = new ArrayList<>();

            for (FinancialRecord r : all) {
                if (r.getDateTime().toLocalDate().equals(date)) {
                    filtered.add(r);
                }
            }

            return formatList(filtered);

        } catch (DaoException e) {
            throw new LogicException("Ошибка DAO", e);
        }
    }

    // ---------------------------------------------------------
    // SHOW ALL
    // ---------------------------------------------------------
    @Override
    public String showAll() throws LogicException {
        try {
            List<FinancialRecord> all = dao.findAll();
            return formatList(all);
        } catch (DaoException e) {
            throw new LogicException("Ошибка DAO", e);
        }
    }

    // ---------------------------------------------------------
    // SHOW PERIOD
    // ---------------------------------------------------------
    @Override
    public String showPeriod(String request) throws LogicException {
        try {
            Map<String, String> params = parse(request);

            LocalDate from = LocalDate.parse(params.get("from"));
            LocalDate to = LocalDate.parse(params.get("to"));

            List<FinancialRecord> all = dao.findAll();
            List<FinancialRecord> filtered = new ArrayList<>();

            for (FinancialRecord r : all) {
                LocalDate d = r.getDateTime().toLocalDate();
                if (!d.isBefore(from) && !d.isAfter(to)) {
                    filtered.add(r);
                }
            }

            return formatList(filtered);

        } catch (DaoException e) {
            throw new LogicException("Ошибка DAO", e);
        }
    }

    // ---------------------------------------------------------
    // PARSER
    // ---------------------------------------------------------
    private Map<String, String> parse(String request) {
        Map<String, String> map = new HashMap<>();

        String[] lines = request.split("\n");
        for (String line : lines) {
            if (line.contains("=")) {
                String[] parts = line.split("=");
                map.put(parts[0].trim(), parts[1].trim());
            }
        }

        return map;
    }

    // ---------------------------------------------------------
    // FORMAT OUTPUT
    // ---------------------------------------------------------
    private String formatList(List<FinancialRecord> list) {
        if (list.isEmpty()) {
            return "Нет записей.";
        }

        StringBuilder sb = new StringBuilder();
        for (FinancialRecord r : list) {
            sb.append("ID: ").append(r.getId()).append("\n")
                    .append("Тип: ").append(r.getType()).append("\n")
                    .append("Категория: ").append(r.getCategory()).append("\n")
                    .append("Сумма: ").append(r.getAmount()).append("\n")
                    .append("Описание: ").append(r.getDescription()).append("\n")
                    .append("Дата: ").append(r.getDateTime()).append("\n")
                    .append("---------------------------\n");
        }

        return sb.toString();
    }

    @Override
    public String showBalance() throws LogicException {
        try {
            List<FinancialRecord> all = dao.findAll();
            double balance = 0;

            for (FinancialRecord r : all) {
                if (r.getType() == RecordType.INCOME) {
                    balance += r.getAmount();
                } else {
                    balance -= r.getAmount();
                }
            }

            return "Текущий баланс: " + balance;

        } catch (DaoException e) {
            throw new LogicException("Ошибка DAO", e);
        }
    }
}

