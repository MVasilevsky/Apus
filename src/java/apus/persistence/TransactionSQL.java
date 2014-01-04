package apus.persistence;

import apus.dao.AbstractDAO;
import apus.dao.AccountDAO;
import apus.dao.CallDAO;
import apus.dao.OrganizationDAO;
import apus.dao.PeriodDAO;
import apus.dao.PersonDAO;
import apus.dao.PhoneNumberDAO;
import apus.dao.UserDAO;
import apus.dao.impl.AccountDAOImpl;
import apus.dao.impl.CallDAOImpl;
import apus.dao.impl.OrganizationDAOImpl;
import apus.dao.impl.PeriodDAOImpl;
import apus.dao.impl.PersonDAOImpl;
import apus.dao.impl.PhoneNumberDAOImpl;
import apus.dao.impl.UserDAOImpl;
import apus.entity.AbstractEntity;
import apus.exception.DatabaseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Max
 */
public class TransactionSQL extends Transaction {

    private Connection connection;
    private static final Map<Class<? extends AbstractDAO<?>>, Class<? extends AbstractDAO<?>>> DAOS = new ConcurrentHashMap<>();

    public TransactionSQL() {
        connection = ConnectionPool.getInstance().getConnection();
    }

    static {
        DAOS.put(AccountDAO.class, AccountDAOImpl.class);
        DAOS.put(CallDAO.class, CallDAOImpl.class);
        DAOS.put(OrganizationDAO.class, OrganizationDAOImpl.class);
        DAOS.put(PeriodDAO.class, PeriodDAOImpl.class);
        DAOS.put(PersonDAO.class, PersonDAOImpl.class);
        DAOS.put(PhoneNumberDAO.class, PhoneNumberDAOImpl.class);
        DAOS.put(UserDAO.class, UserDAOImpl.class);
    }

    @Override
    public <Type extends AbstractDAO<? extends AbstractEntity>> Type createDao(Class<Type> key) throws DatabaseException {

        Class<? extends AbstractDAO<? extends AbstractEntity>> value = DAOS.get(key);
        if (value != null) {
            try {
                AbstractDAO<?> dao = value.getConstructor(Connection.class).newInstance(connection);
                return (Type) dao;
            } catch (Exception e) {
                throw new DatabaseException(e);
            }
        }
        return null;
    }

    @Override
    public void commit() throws DatabaseException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void rollback() throws DatabaseException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void close() throws DatabaseException {
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}
