package apus.persistence;

import apus.exception.DatabaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Max
 */
public class ConnectionPool {

    private final static Logger LOG = Logger.getLogger(ConnectionPool.class.getName());
    private static ConnectionPool pool = new ConnectionPool();
    private static ConcurrentLinkedQueue<Connection> connections;
    private static int poolSize;
    private static String url;
    private static String login;
    private static String password;
    
    private ConnectionPool() {
    }
    
    public static void init(String dbUrl, String dbLogin, String dbPassword, String dbDriver, int cpSize) throws DatabaseException {

        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            LOG.log(Level.SEVERE, "Can't find database driver: {0}", e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        url = dbUrl;
        login = dbLogin;
        password = dbPassword;
        poolSize = cpSize;
        connections = new ConcurrentLinkedQueue<>();
        try {
            Connection connection;
            for (int i = 0; i < poolSize; i++) {
                connection = DriverManager.getConnection(url, login, password);
                connection.setAutoCommit(false);
                connections.add(connection);
            }
        } catch (SQLException e2) {
            LOG.log(Level.SEVERE, "Can't get connection to database: {0}", e2.getMessage());
            throw new DatabaseException(e2.getMessage());
        }
    }

    public static ConnectionPool getInstance() {
        return pool;
    }

    public synchronized Connection getConnection() {
        Connection c = null;
        while (c == null) {
            try {
                if (!connections.isEmpty()) {
                    c = connections.remove();
                    if (!c.isValid(0)) {
                        c = null;
                    }
                } else {
                    c = DriverManager.getConnection(url, login, password);
                    c.setAutoCommit(false);
                }
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "Can't get connection to database: {0}", e.getMessage());
            }
        }
        return c;
    }

    public synchronized boolean releaseConnection(Connection connection) {
        if (connections.size() == poolSize) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "Can't release connection: {0}", e.getMessage());
            }
        } else {
            connections.add(connection);
        }
        return false;
    }

    public static synchronized boolean close() {
        if (connections.size() != poolSize) {
            return false;
        }
        boolean error = false;
        for (Connection c : connections) {
            try {
                c.close();
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "Can't close connection: {0}", e.getMessage());
                error = true;
            }
        }
        return !error;
        
    }
}
