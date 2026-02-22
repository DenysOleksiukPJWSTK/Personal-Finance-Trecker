package PFT.util;

import PFT.exception.ReportValidationException;
import PFT.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ReportValidationUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReportValidationUtil.class);

    private ReportValidationUtil(){}

    public static void validate(Transaction[] transactions){
        if (transactions == null){
            logger.error("Transactions cannot be null");
            throw new ReportValidationException("Transactions cannot be null");
        }

        if (transactions.length < 1){
            logger.error("Transactions cannot be empty");
            throw new ReportValidationException("Transactions cannot be empty");
        }
    }

    public static void validate(double totalIncome,double totalExpense){
       if (totalIncome < 0){
           logger.error("Total income cannot be negative");
           throw new ReportValidationException("Total income cannot be negative");
       }

       if (totalExpense < 0){
           logger.error("Total expense cannot be negative");
           throw new ReportValidationException("Total expense cannot be negative");
       }
    }

    public static void validate(Transaction transaction){
        if (transaction == null){
            logger.error("Transaction cannot be null");
            throw new ReportValidationException("Transaction cannot be null");
        }
    }
}
