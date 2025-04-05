package model;

import java.time.LocalDate;
import java.util.UUID;

public class Expense {
    private final UUID id;
    private final String title;
    private final double amount;
    private final LocalDate date;
    private final Category category;

    public Expense(String title, double amount, LocalDate date, Category category) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }
}
