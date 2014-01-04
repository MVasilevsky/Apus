package apus.persistence;

import apus.exception.DatabaseException;

/**
 * @author Max
 */
public class TransactionFactory {

    public static Transaction createSQLTransaction() throws DatabaseException {

        return new TransactionSQL();
    }
}
