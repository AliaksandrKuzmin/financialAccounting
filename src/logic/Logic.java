package logic;

public interface Logic {
    String addRecord(String request) throws LogicException;
    String updateRecord(String request) throws LogicException;
    String findByCategory(String request) throws LogicException;
    String findByDate(String request) throws LogicException;
    String showAll() throws LogicException;
    String showPeriod(String request) throws LogicException;
    String showBalance() throws LogicException;
}

