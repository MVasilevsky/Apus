package apus.persistence;

import apus.dao.impl.AbstractDAOImpl;
import apus.exception.DatabaseException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author M.Vasilevsky
 */
public class DataBaseStatistics {

    private final static Logger logger = Logger.getLogger(AbstractDAOImpl.class.getName());

    public static Map<String, Integer> getStatistics() {
        Map<String, Integer> statistics = new HashMap<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        Statement st = null;
        ResultSet rs;

        try {
            st = connection.createStatement();

            rs = st.executeQuery("SELECT COUNT(*) AS COU FROM SUBSCRIBER WHERE SUBSCRIBER_TYPE=0");
            if (rs.next()) {
                statistics.put("Persons", rs.getInt("COU"));
            }

            rs = st.executeQuery("SELECT COUNT(*) AS COU FROM SUBSCRIBER WHERE SUBSCRIBER_TYPE=1");
            if (rs.next()) {
                statistics.put("Organizations", rs.getInt("COU"));
            }

            rs = st.executeQuery("SELECT COUNT(*) AS COU FROM ACCOUNT");
            if (rs.next()) {
                statistics.put("Accounts", rs.getInt("COU"));
            }

            rs = st.executeQuery("SELECT COUNT(*) AS COU FROM PHONE_NUMBER");
            if (rs.next()) {
                statistics.put("Phone numbers", rs.getInt("COU"));
            }

            rs = st.executeQuery("SELECT COUNT(*) AS COU FROM PHONE_CALL");
            if (rs.next()) {
                statistics.put("Calls", rs.getInt("COU"));
            }

            logger.info("Statistics obtained");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Can't read statistics: {0}", ex.getMessage());
            throw new DatabaseException("Error while database statistics obtaining");
        } finally {
            if (st != null) {
                try {
                    st.close();
                    ConnectionPool.getInstance().releaseConnection(connection);
                } catch (SQLException ex) {
                    ConnectionPool.getInstance().releaseConnection(connection);
                    throw new DatabaseException("Can't close statement");
                }
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return statistics;
    }
}
