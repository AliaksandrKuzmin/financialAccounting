package dao.impl;

import dao.DaoException;
import dao.FinancialRecordDao;
import entity.FinancialRecord;
import entity.RecordType;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileFinancialRecordDao implements FinancialRecordDao {

    private static final String FILE_PATH = "resources/financial_records.txt";

    @Override
    public void add(FinancialRecord record) throws DaoException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(serialize(record));
            writer.newLine();
        } catch (IOException e) {
            throw new DaoException("Ошибка записи в файл", e);
        }
    }

    @Override
    public void update(FinancialRecord updatedRecord) throws DaoException {
        List<FinancialRecord> all = findAll();
        boolean found = false;

        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == updatedRecord.getId()) {
                all.set(i, updatedRecord);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new DaoException("Запись с id=" + updatedRecord.getId() + " не найдена", null);
        }

        writeAll(all);
    }

    @Override
    public List<FinancialRecord> findAll() throws DaoException {
        List<FinancialRecord> records = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return records;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(deserialize(line));
            }
        } catch (IOException e) {
            throw new DaoException("Ошибка чтения файла", e);
        }

        return records;
    }

    private void writeAll(List<FinancialRecord> records) throws DaoException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (FinancialRecord r : records) {
                writer.write(serialize(r));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new DaoException("Ошибка перезаписи файла", e);
        }
    }

    private String serialize(FinancialRecord r) {
        return r.getId() + ";" +
                r.getType() + ";" +
                r.getCategory() + ";" +
                r.getAmount() + ";" +
                r.getDescription() + ";" +
                r.getDateTime();
    }

    private FinancialRecord deserialize(String line) {
        String[] parts = line.split(";");
        int id = Integer.parseInt(parts[0]);
        RecordType type = RecordType.valueOf(parts[1]);
        String category = parts[2];
        double amount = Double.parseDouble(parts[3]);
        String description = parts[4];
        LocalDateTime dateTime = LocalDateTime.parse(parts[5]);

        return new FinancialRecord(id, type, category, amount, description, dateTime);
    }

    public int generateId() throws DaoException {
        List<FinancialRecord> all = findAll();
        int max = 0;
        for (FinancialRecord r : all) {
            if (r.getId() > max) {
                max = r.getId();
            }
        }
        return max + 1;
    }
}


