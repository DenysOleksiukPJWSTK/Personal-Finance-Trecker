package PFT.service;

import PFT.exception.InvalidTransactionException;
import PFT.model.Transaction;
import PFT.util.Cloner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final static int INITIAL_SIZE = 10;
    private Transaction[] transactions = new Transaction[INITIAL_SIZE];
    private int capacity = INITIAL_SIZE;
    private int size = 0;


    public TransactionService() {}

    public void add(Transaction transaction) {
        if (transaction == null) {
            logger.error("Transaction is null");
            throw new NullPointerException("Transaction is null");
        }
        if (size == capacity) expand();
        transactions[size++] = transaction;
    }

    public void remove(Transaction transaction) {
        if (transaction == null) {
            logger.error("Transaction is null");
            throw new NullPointerException("Transaction is null");
        }

        for (int i = 0; i < size; i++){
            if (transactions[i].equals(transaction)) {
                for (int j = i;j < size-1;j++) transactions[j] = transactions[j+1];
                transactions[size-1] = null;
                size--;
                return;
            }
        }
        logger.warn("Transaction not found!");
        throw new InvalidTransactionException("Transaction not found!");
    }

    public Transaction[] getAll() {
        Transaction[] result = new Transaction[size];
        System.arraycopy(transactions, 0, result, 0, size);
        return result;
    }

    public Transaction[] filterByDate(LocalDate start, LocalDate end){

        if (start == null || end == null) throw new InvalidTransactionException("Date is null");

        if (start.isAfter(end)) throw new InvalidTransactionException("Start date is after end date");

        int count = 0;

        for (int i = 0; i < size; i++)
            if (!transactions[i].getDate().isBefore(start) && !transactions[i].getDate().isAfter(end)) count++;


        if (count == 0) throw new InvalidTransactionException("No transactions found");

        Transaction[] result = new Transaction[count];

        int index = 0;
        for (int i = 0; i < size; i++)
            if (!transactions[i].getDate().isBefore(start)
                    && !transactions[i].getDate().isAfter(end))
                result[index++] = transactions[i];

        return result;
    }

    private void expand(){
        int newCapacity = capacity*2;

        Transaction[] newTransactions = new Transaction[newCapacity];

        System.arraycopy(transactions, 0, newTransactions, 0, size);

        transactions = newTransactions;
        capacity = newCapacity;
    }

    public Transaction cloneTransaction(int id){
        for (int i = 0; i < size; i++) if (transactions[i].getId() == id) return transactions[i].deepClone();
        logger.error("Cloning failed!");
        throw new InvalidTransactionException("Cloning failed!");
    }
}
