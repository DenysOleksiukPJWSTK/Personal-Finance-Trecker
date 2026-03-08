package PFT.service;

import PFT.model.Expense;
import PFT.model.Income;
import PFT.model.Transaction;
import PFT.report.Report;

import java.time.LocalDate;

public class ReportService {

    public Report generateReport(Transaction[] transactions){
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");

        double income = 0, expense = 0;

        for (Transaction transaction : transactions) {
            if (transaction == null) continue;
            if (transaction instanceof Income){
                income += (transaction).getAmount();
            }else {
                expense += (transaction).getAmount();
            }
        }

        return new Report(transactions, expense, income);
    }

    public Report generateReportByDate(Transaction[] transactions, LocalDate date){
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");

        if (date == null) throw new IllegalArgumentException("Date cannot be null");

        int count = 0;

        for (Transaction transaction : transactions) if (transaction != null && transaction.getDate().equals(date)) count++;

        Transaction[] filtered = new Transaction[count];

        int index = 0;
        double income = 0;
        double expense = 0;

        for (Transaction transaction : transactions) {
            if (transaction == null) continue;

            if (transaction.getDate().equals(date)) {
                filtered[index++] = transaction;

                if (transaction instanceof Income) income += transaction.getAmount();
                else expense += transaction.getAmount();
            }
        }

        return new Report(filtered, expense, income);
    }

    public Report generateReportByAmount(Transaction[] transactions,double amount){
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");

        int count = 0;

        for (Transaction transaction : transactions) if (transaction != null && transaction.getAmount() == amount) count++;

        Transaction[] filtered = new Transaction[count];

        int index = 0;
        double income = 0;
        double expense = 0;

        for (Transaction transaction : transactions) {
            if (transaction == null) continue;

            if (transaction.getAmount() == amount){
                filtered[index++] = transaction;

                if (transaction instanceof Income) income += transaction.getAmount();
                else expense += transaction.getAmount();
            }
        }

        return new Report(filtered, expense, income);
    }
}
