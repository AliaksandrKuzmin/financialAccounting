package dao;

import dao.impl.FileFinancialRecordDao;

public class DaoProvider {
    private static final FinancialRecordDao instance = new FileFinancialRecordDao();
    public static FinancialRecordDao getInstance() { return instance; }
}

