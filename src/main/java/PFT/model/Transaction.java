package PFT.model;


import PFT.util.Cloner;
import PFT.util.TransactionValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public abstract class Transaction implements Cloneable, Cloner {
    private static int counter = 0;
    private final int id;
    private final double amount;
    private final LocalDate date;
    private final String description;

    private static final Logger logger = LoggerFactory.getLogger(Transaction.class);

    private int nextId(){
        return counter++;
    }

    public Transaction(double amount, LocalDate date, String description) {
        TransactionValidationUtil.validate(amount);
        TransactionValidationUtil.validate(date);
        TransactionValidationUtil.validate(description);

        this.id = nextId();
        this.amount = amount;
        this.date = date;
        this.description = description;
        logger.info("Transaction successful created!");
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof  Transaction o1)) return false;
        return  id == o1.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "Transaction: | Id: " + id + " | Amount: " + amount + " | Date: " + date.toString() + " | Description: " + description;
    }

    @Override
    public Transaction shallowClone() {
        return clone();
    }

    @Override
    public Transaction deepClone() {
        return clone();
    }

    @Override
    public abstract Transaction clone();

}
