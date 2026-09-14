package entity;

import java.time.LocalDateTime;

public class FinancialRecord {

    private final int id;
    private final RecordType type;
    private final String category;
    private final double amount;
    private final String description;
    private final LocalDateTime dateTime;

    public FinancialRecord(int id,
                           RecordType type,
                           String category,
                           double amount,
                           String description,
                           LocalDateTime dateTime) {

        this.id = id;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public RecordType getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", type=" + type +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}


