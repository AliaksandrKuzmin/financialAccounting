package dao;

import entity.FinancialRecord;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordDao {

    FinancialRecord add(FinancialRecord record) throws DaoException;

    FinancialRecord update(FinancialRecord record) throws DaoException;

    void delete(int id) throws DaoException;

    List<FinancialRecord> findAll() throws DaoException;

    List<FinancialRecord> findByCategory(String category) throws DaoException;

    List<FinancialRecord> findByDate(LocalDate date) throws DaoException;

    List<FinancialRecord> findByPeriod(LocalDate from, LocalDate to) throws DaoException;
}


