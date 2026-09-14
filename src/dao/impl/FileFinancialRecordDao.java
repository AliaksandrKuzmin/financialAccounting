package dao.impl;

import dao.DaoException;
import dao.FinancialRecordDao;
import entity.FinancialRecord;
import entity.RecordType;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileFinancialRecordDao implements FinancialRecordDao {

    private static final Logger logger =
            Logger.getLogger(FileFinancialRecordDao.class.getName());

    private final File file;

    public FileFinancialRecordDao(String path) {
        this.file = new File(path);
    }

    @Override
    public FinancialRecord add(FinancialRecord record) throws DaoException {
        try {
            List<FinancialRecord> all = findAll();
            all.add(record);
            writeAll(all);
            return record;
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Ошибка добавления записи: " + record, e);
            throw e;
        }
    }

    @Override
    public FinancialRecord update(FinancialRecord record) throws DaoException {
        try {
            List<FinancialRecord> all = findAll();

            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).getId() == record.getId()) {
                    all.set(i, record);
                    writeAll(all);
                    return record;
                }
            }

            throw new DaoException("Запись не найдена: id=" + record.getId());

        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Ошибка обновления записи: " + record, e);
            throw e;
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            List<FinancialRecord> all = findAll();
            all.removeIf(r -> r.getId() == id);
            writeAll(all);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Ошибка удаления записи id=" + id, e);
            throw e;
        }
    }

    @Override
    public List<FinancialRecord> findAll() throws DaoException {
        List<FinancialRecord> list = new ArrayList<>();

        if (!file.exists()) {
            logger.log(Level.INFO, "Файл не найден, возвращаю пустой список: " + file.getAbsolutePath());
            return list;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                list.add(deserialize(line));
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка чтения файла: " + file.getAbsolutePath(), e);
            throw new DaoException("Ошибка чтения файла", e);
        }

        return list;
    }

    @Override
    public List<FinancialRecord> findByCategory(String category) throws DaoException {
        try {
            List<FinancialRecord> all = findAll();
            List<FinancialRecord> res = new ArrayList<>();

            for (FinancialRecord r : all) {
                if (r.getCategory().equalsIgnoreCase(category)) {
                    res.add(r);
                }
            }

            return res;

        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Ошибка поиска по категории: " + category, e);
            throw e;
        }
    }

    @Override
    public List<FinancialRecord> findByDate(LocalDate date) throws DaoException {
        try {
            List<FinancialRecord> all = findAll();
            List<FinancialRecord> res = new ArrayList<>();

            for (FinancialRecord r : all) {
                if (r.getDateTime().toLocalDate().equals(date)) {
                    res.add(r);
                }
            }

            return res;

        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Ошибка поиска по дате: " + date, e);
            throw e;
        }
    }

    @Override
    public List<FinancialRecord> findByPeriod(LocalDate from, LocalDate to) throws DaoException {
        try {
            List<FinancialRecord> all = findAll();
            List<FinancialRecord> res = new ArrayList<>();

            for (FinancialRecord r : all) {
                LocalDate d = r.getDateTime().toLocalDate();
                if (!d.isBefore(from) && !d.isAfter(to)) {
                    res.add(r);
                }
            }

            return res;

        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Ошибка поиска по периоду: " + from + " - " + to, e);
            throw e;
        }
    }

    private void writeAll(List<FinancialRecord> list) throws DaoException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (FinancialRecord r : list) {
                bw.write(serialize(r));
                bw.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка записи файла: " + file.getAbsolutePath(), e);
            throw new DaoException("Ошибка записи файла", e);
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
        String[] p = line.split(";");

        int id = Integer.parseInt(p[0]);
        RecordType type = RecordType.valueOf(p[1]);
        String category = p[2];
        double amount = Double.parseDouble(p[3]);
        String description = p[4];
        LocalDateTime dt = LocalDateTime.parse(p[5]);

        return new FinancialRecord(id, type, category, amount, description, dt);
    }
}



