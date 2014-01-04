package apus.persistence;

import apus.dao.AbstractDAO;
import apus.entity.AbstractEntity;
import apus.exception.DatabaseException;

/**
 * @author Max
 */
public abstract class Transaction {

    abstract public <Type extends AbstractDAO<? extends AbstractEntity>> Type createDao(Class<Type> key) throws DatabaseException;

    abstract public void commit() throws DatabaseException;

    abstract public void rollback() throws DatabaseException;

    abstract public void close() throws DatabaseException;
}
