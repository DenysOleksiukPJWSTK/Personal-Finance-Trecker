package PFT.model;

import java.time.LocalDate;

public final class Expense extends Transaction {

    public Expense(double amount, LocalDate date, String description) {
        super(amount, date, description);
    }

    @Override
    public Transaction clone(){
        return new Expense(
                this.getAmount(),
                this.getDate(),
                this.getDescription()
        );
    }

    @Override
    public String toString() {
        return "Expense:" +
                " | Id: " + this.getId() +
                " | Amount: " + this.getAmount() +
                " | Date: " + this.getDate().toString() +
                " | Description: " + this.getDescription();
    }
}
