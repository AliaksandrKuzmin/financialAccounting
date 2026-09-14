package logic;

import entity.FinancialRecord;
import entity.RecordType;

import java.time.LocalDate;
import java.util.List;

public interface RecordLogic {

    FinancialRecord addRecord(
            RecordType type,
            String category,
            double amount,
            String description
    ) throws LogicException;

    FinancialRecord updateRecord(
            int id,
            String newCategory,
            double newAmount,
            String newDescription
    ) throws LogicException;

    void deleteRecord(int id) throws LogicException;

    List<FinancialRecord> findByCategory(String category) throws LogicException;

    List<FinancialRecord> findByDate(LocalDate date) throws LogicException;

    List<FinancialRecord> findByPeriod(LocalDate from, LocalDate to) throws LogicException;

    List<FinancialRecord> showAll() throws LogicException;

    double showBalance() throws LogicException;
}