package PFT.service;

import PFT.model.Expense;
import PFT.model.Income;
import PFT.model.Transaction;
import PFT.report.Report;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReportServiceTest {

    private ReportService reportService;
    private Expense expense;
    private Income income;

    @BeforeEach
    void setUp() {
        reportService = new ReportService();

        expense = new Expense(500, LocalDate.now(), "food");
        income = new Income(1000, LocalDate.now(), "salary");
    }

    @Test
    void generateReport_shouldCalculateIncomeAndExpense() {

        Transaction[] transactions = {expense, income};

        Report report = reportService.generateReport(transactions);

        assertNotNull(report);
        assertEquals(1000, report.getTotalIncome());
        assertEquals(500, report.getTotalExpense());
        assertEquals(2, report.getTransactions().length);
    }

    @Test
    void generateReport_shouldThrowExceptionIfTransactionsNull() {

        assertThrows(
                IllegalArgumentException.class,
                () -> reportService.generateReport(null)
        );
    }

    @Test
    void generateReportByDate_shouldReturnTransactionsForGivenDate() {

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        Transaction t1 = new Expense(500, today, "food");
        Transaction t2 = new Income(1000, yesterday, "salary");

        Transaction[] transactions = {t1, t2};

        Report report = reportService.generateReportByDate(transactions, today);

        assertNotNull(report);
        assertEquals(1, report.getTransactions().length);
        assertEquals(0, report.getTotalIncome());
        assertEquals(500, report.getTotalExpense());
    }

    @Test
    void generateReportByDate_shouldThrowExceptionIfTransactionsNull() {

        LocalDate today = LocalDate.now();

        assertThrows(
                IllegalArgumentException.class,
                () -> reportService.generateReportByDate(null, today)
        );
    }

    @Test
    void generateReportByDate_shouldThrowExceptionIfDateNull() {

        Transaction[] transactions = {expense, income};

        assertThrows(
                IllegalArgumentException.class,
                () -> reportService.generateReportByDate(transactions, null)
        );
    }

    @Test
    void generateReportByAmount_shouldReturnTransactionsWithGivenAmount() {

        Transaction[] transactions = {expense, income};

        Report report = reportService.generateReportByAmount(transactions, 1000);

        assertNotNull(report);
        assertEquals(1, report.getTransactions().length);
        assertEquals(1000, report.getTotalIncome());
        assertEquals(0, report.getTotalExpense());
    }

    @Test
    void generateReportByAmount_shouldThrowExceptionIfTransactionsNull() {

        assertThrows(
                IllegalArgumentException.class,
                () -> reportService.generateReportByAmount(null, 1000)
        );
    }
}