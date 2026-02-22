package PFT.model;

import PFT.exception.TransactionValidationException;
import PFT.util.TransactionValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public abstract class Transaction implements Cloneable {
    private final int id;
    private final double amount;
    private final LocalDate date;
    private final String description;

    private static final Logger logger = LoggerFactory.getLogger(Transaction.class);


    public Transaction(int id, double amount, LocalDate date, String description){
        TransactionValidationUtil.validate(id);
        TransactionValidationUtil.validate(amount);
        TransactionValidationUtil.validate(date);
        TransactionValidationUtil.validate(description);
        this.id = id;
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
    public String toString() {
        return "Transaction: | Id: " + id + " | Amount: " + amount + " | Date: " + date.toString() + " | Description: " + description;
    }

    @Override
    public abstract Transaction clone();

}
