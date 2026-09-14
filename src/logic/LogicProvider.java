package logic;

import dao.FinancialRecordDao;
import dao.impl.FileFinancialRecordDao;
import logic.impl.RecordLogicImpl;

public class LogicProvider {
    private static final LogicProvider instance = new LogicProvider();

    private final RecordLogic logic;

    private LogicProvider() {
        FinancialRecordDao dao = new FileFinancialRecordDao("financial_records.txt");
        this.logic = new RecordLogicImpl(dao);
    }

    public static LogicProvider getInstance() {
        return instance;
    }

    public RecordLogic getLogic() {
        return logic;
    }
}

