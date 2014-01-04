package apus.service.impl;

import apus.entity.AbstractEntity;
import apus.exception.DatabaseException;
import apus.persistence.Transaction;
import apus.service.Service;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Max
 */
public abstract class ServiceImpl<TypeEn extends AbstractEntity> implements Service<TypeEn> {

    private Transaction transaction = null;

    public ServiceImpl(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public void close() {
        try {
            transaction.close();
        } catch (DatabaseException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
}
