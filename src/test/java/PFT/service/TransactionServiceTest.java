package PFT.service;

import PFT.exception.InvalidTransactionException;
import PFT.model.Income;
import PFT.model.Transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionServiceTest {
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService();
    }

    @Test
    void add_shouldIncreaseSize(){
        Transaction transaction = new Income(1000, LocalDate.now(),"Salary");
        transactionService.add(transaction);
        assertEquals(1,transactionService.getAll().length);
    }

    @Test
    void add_null_shouldTrowException(){
        assertThrows(NullPointerException.class, () -> transactionService.add(null));
    }

    @Test
    void remove_shouldRemoveTransaction(){
        Transaction transaction = new Income(1000, LocalDate.now(),"Salary");
        transactionService.add(transaction);
        transactionService.remove(transaction);
        assertEquals(0,transactionService.getAll().length);
    }

    @Test
    void remove_notExisting_shouldThrowException(){
        Transaction transaction = new Income(1000, LocalDate.now(),"Salary");
        assertThrows(InvalidTransactionException.class, () -> transactionService.remove(transaction));
    }

    @Test
    void filterByDate_shouldReturnCorrectTransactions(){
        LocalDate now = LocalDate.now();

        Transaction t1 = new Income(1000, now.minusDays(1),"Salary");
        Transaction t2 = new Income(1000, now,"Salary");

        transactionService.add(t1);
        transactionService.add(t2);

        Transaction[] result = transactionService.filterByDate(now.minusDays(2),now.plusDays(1));
        assertEquals(2,result.length);
    }

    @Test
    void filterByDate_noMatches_shouldThrowException(){
        LocalDate now = LocalDate.now();

        Transaction t1 = new Income(1000, now.minusDays(10),"Salary");
        transactionService.add(t1);

        assertThrows(InvalidTransactionException.class, () -> transactionService.filterByDate(now.minusDays(2),now));
    }

    @Test
    void cloneTransaction_shouldReturnNewInstance(){
        Transaction transaction = new Income(1000, LocalDate.now(),"Salary");
        transactionService.add(transaction);

        Transaction cloned = transactionService.cloneTransaction(transaction.getId());

        assertNotSame(transaction,cloned);
        assertEquals(transaction.getAmount(),cloned.getAmount());
        assertEquals(transaction.getDate(),cloned.getDate());
        assertEquals(transaction.getDescription(),cloned.getDescription());
    }

    @Test
    void cloneTransaction_invalidId_shouldThrowException(){
        assertThrows(InvalidTransactionException.class, () -> transactionService.cloneTransaction(-1));
    }

    @Test
    void expand_shouldIncreaseCapacity(){
        for (int i = 0; i < 15; i++) {
            transactionService.add(new Income(1000, LocalDate.now(),"Salary"));
        }

        assertEquals(15,transactionService.getAll().length);
    }



}

