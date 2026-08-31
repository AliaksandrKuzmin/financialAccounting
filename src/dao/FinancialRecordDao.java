package dao;

import entity.FinancialRecord;

import java.util.List;

public interface FinancialRecordDao {
    void add(FinancialRecord record) throws DaoException;
    void update(FinancialRecord record) throws DaoException;
    List<FinancialRecord> findAll() throws DaoException;
    int generateId() throws DaoException;
}

