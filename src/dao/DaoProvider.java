package dao;

import dao.impl.FileFinancialRecordDao;

public class DaoProvider {

    private static final FinancialRecordDao instance =
            new FileFinancialRecordDao("financial_records.txt");

    private DaoProvider() {}

    public static FinancialRecordDao getInstance() {
        return instance;
    }
}


