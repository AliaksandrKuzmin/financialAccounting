package logic.impl;

import dao.DaoException;
import dao.DaoProvider;
import dao.FinancialRecordDao;
import entity.FinancialRecord;
import entity.RecordType;
import logic.LogicException;
import logic.RecordLogic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RecordLogicImpl implements RecordLogic {

    private final FinancialRecordDao dao;
    private int nextId;

    public RecordLogicImpl(FinancialRecordDao dao) {
        this.dao = dao;
        this.nextId = initNextId();
    }

    private int initNextId() {
        try {
            List<FinancialRecord> all = dao.findAll();
            return all.stream()
                    .mapToInt(FinancialRecord::getId)
                    .max()
                    .orElse(0) + 1;
        } catch (DaoException e) {
            throw new LogicException("Ошибка инициализации nextId", e);
        }
    }

    @Override
    public FinancialRecord addRecord(RecordType type,
                                     String category,
                                     double amount,
                                     String description) throws LogicException {

        FinancialRecord record = new FinancialRecord(
                nextId++,
                type,
                category,
                amount,
                description,
                LocalDateTime.now()
        );

        try {
            return dao.add(record);
        } catch (DaoException e) {
            throw new LogicException("Ошибка добавления записи", e);
        }
    }

    @Override
    public FinancialRecord updateRecord(int id,
                                        String category,
                                        double amount,
                                        String description) throws LogicException {

        FinancialRecord updated = new FinancialRecord(
                id,
                findType(id),
                category,
                amount,
                description,
                LocalDateTime.now()
        );

        try {
            return dao.update(updated);
        } catch (DaoException e) {
            throw new LogicException("Ошибка обновления записи", e);
        }
    }

    private RecordType findType(int id) throws LogicException {
        try {
            return dao.findAll().stream()
                    .filter(r -> r.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new LogicException("Запись не найдена: id=" + id))
                    .getType();
        } catch (DaoException e) {
            throw new LogicException("Ошибка поиска типа записи", e);
        }
    }

    @Override
    public void deleteRecord(int id) throws LogicException {
        try {
            dao.delete(id);
        } catch (DaoException e) {
            throw new LogicException("Ошибка удаления записи", e);
        }
    }

    @Override
    public List<FinancialRecord> findByCategory(String category) throws LogicException {
        try {
            return dao.findByCategory(category);
        } catch (DaoException e) {
            throw new LogicException("Ошибка поиска по категории", e);
        }
    }

    @Override
    public List<FinancialRecord> findByDate(LocalDate date) throws LogicException {
        try {
            return dao.findByDate(date);
        } catch (DaoException e) {
            throw new LogicException("Ошибка поиска по дате", e);
        }
    }

    @Override
    public List<FinancialRecord> findByPeriod(LocalDate from, LocalDate to) throws LogicException {
        try {
            return dao.findByPeriod(from, to);
        } catch (DaoException e) {
            throw new LogicException("Ошибка поиска по периоду", e);
        }
    }

    @Override
    public List<FinancialRecord> showAll() throws LogicException {
        try {
            return dao.findAll();
        } catch (DaoException e) {
            throw new LogicException("Ошибка получения всех записей", e);
        }
    }

    @Override
    public double showBalance() throws LogicException {
        try {
            return dao.findAll().stream()
                    .mapToDouble(r -> r.getType() == RecordType.INCOME
                            ? r.getAmount()
                            : -r.getAmount())
                    .sum();
        } catch (DaoException e) {
            throw new LogicException("Ошибка расчёта баланса", e);
        }
    }
}



