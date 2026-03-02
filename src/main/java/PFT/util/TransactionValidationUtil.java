package PFT.util;

import PFT.exception.TransactionValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public final class TransactionValidationUtil {
    private TransactionValidationUtil(){}

    private static final Logger logger = LoggerFactory.getLogger(TransactionValidationUtil.class);

    public static void validate(double amount){
        if (amount < 0){
            logger.error("Invalid transaction amount --> must be greater equals 0!");
            throw new TransactionValidationException("Invalid Transaction Amount");
        }
    }

    public static void validate(String description){
        if (description == null){
            logger.error("Invalid transaction description --> cannot be null!");
            throw new TransactionValidationException("Invalid Transaction Description");
        }
        if (description.trim().isEmpty()){
            logger.error("Invalid transaction description --> cannot be empty!");
            throw new TransactionValidationException("Invalid Transaction Description");
        }
        if (description.trim().length() > 10000){
            logger.error("Invalid transaction description --> cannot be longer than 10000 symbols!");
            throw new TransactionValidationException("Invalid Transaction Description");
        }
    }

    public static void validate(LocalDate date){
        if (date == null){
            logger.error("Invalid transaction date --> cannot be null!");
            throw new TransactionValidationException("Invalid Transaction Date");
        }
    }

}
