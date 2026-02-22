package PFT.model;

import java.time.LocalDate;

public final class Income extends Transaction {

    public Income(int id,double amount,LocalDate date, String description) {
        super(id, amount, date, description);
    }

    @Override
    public Transaction clone(){
        return new Income(
                this.getId(),
                this.getAmount(),
                this.getDate(),
                this.getDescription()
        );
    }

    @Override
    public String toString() {
        return "Income:" +
                " | Id: " + this.getId() +
                " | Amount: " + this.getAmount() +
                " | Date: " + this.getDate().toString() +
                " | Description: " + this.getDescription();
    }


}
