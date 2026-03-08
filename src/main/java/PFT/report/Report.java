package PFT.report;

import PFT.model.Income;
import PFT.model.Transaction;
import PFT.util.ReportValidationUtil;

public final class Report {
    private Transaction[] transactions;
    private double totalExpense;
    private double totalIncome;

    public Report(Transaction[] transactions, double totalExpense, double totalIncome) {
        ReportValidationUtil.validate(transactions);
        ReportValidationUtil.validate(totalIncome,totalExpense);
        this.transactions = new Transaction[transactions.length];
        System.arraycopy(transactions, 0, this.transactions, 0, transactions.length);
        this.totalExpense = totalExpense;
        this.totalIncome = totalIncome;
    }

    public String generateReport(){
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------Report---------------------------\n");
        for (Transaction transaction : transactions){
            if (transaction == null) continue;
            sb.append(transaction).append("\n");
        }
        sb.append("Total income: ").append(totalIncome).append("\n");
        sb.append("Total expense: ").append(totalExpense).append("\n");
        sb.append("-------------------------------------------------------\n");
        return sb.toString();
    }

    public void addTransaction(Transaction transaction){
        ReportValidationUtil.validate(transaction);

        if (transaction instanceof Income) totalIncome = totalIncome + transaction.getAmount();
        else totalExpense = totalExpense + transaction.getAmount();

        expand();
        transactions[transactions.length-1] = transaction;
    }

    private void expand(){
        int length = transactions.length;
        Transaction[] newTransactions = new Transaction[length + 1];
        System.arraycopy(transactions, 0, newTransactions, 0, length);
        transactions = newTransactions;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }
    public double getTotalExpense() {
        return totalExpense;
    }
    public double getTotalIncome() {
        return totalIncome;
    }

}
