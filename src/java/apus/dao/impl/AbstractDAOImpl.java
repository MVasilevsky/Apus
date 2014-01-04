package apus.dao.impl;

import apus.entity.AbstractEntity;
import apus.exception.DatabaseException;
import apus.dao.AbstractDAO;
import java.sql.*;
import java.util.logging.Logger;

/**
 * @author Roman Dyatkovsky
 * @author Maxim Vasilevsky
 * @since APUS v0.5
 */
public abstract class AbstractDAOImpl<TypeEn extends AbstractEntity> implements AbstractDAO<TypeEn> {

    protected Connection connection;
    private final static Logger logger = Logger.getLogger(AbstractDAOImpl.class.getName());

    public AbstractDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public int getNextId() {
        Statement st = null;
        Statement st2 = null;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            st2 = connection.createStatement();
            rs = st.executeQuery("select PVALUE from PARAMS where PKEY='ENTITY_ID'");
            int id = 0;
            if (rs.next()) {
                id = Integer.parseInt(rs.getString("PVALUE").trim());
            } else {
                st.execute("INSERT INTO PARAMS (PKEY,PVALUE) VALUES ('ENTITY_ID','0')");
                connection.commit();
            }
            id++;
            st.execute("update PARAMS set PVALUE=" + id + " where PKEY='ENTITY_ID'");
            connection.commit();
            return id;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new DatabaseException("Can't create new ID", e);
        } finally {
            
            if (st != null) {
                try {
                    st.close();
                } catch (Exception e){}
            }

            if (st2 != null) {
                try {
                    st2.close();
                } catch (Exception e){}
            }
        }
    }

    public int getNextUser() {
        Statement st = null;
        Statement st2 = null;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            st2 = connection.createStatement();
            st2.execute("START TRANSACTION");
            rs = st.executeQuery("SELECT PVALUE FROM PARAMS WHERE PKEY='USER_ID'");
            int id = 0;
            if (rs.next()) {
                id = Integer.parseInt(rs.getString("PVALUE").trim());
            } else {
                st.execute("INSERT INTO PARAMS (PKEY,PVALUE) VALUES ('USER_ID','0')");
                st2.execute("COMMIT");
            }
            id++;
            st.execute("update PARAMS set PVALUE=" + id + " where PKEY='USER_ID'");
            st2.execute("COMMIT");
            return id;
        } catch (SQLException e) {
            throw new DatabaseException("Can't create new user", e);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                System.err.println("Can't close statement");
                ex.printStackTrace(System.err);
            }
        }
    }
}
