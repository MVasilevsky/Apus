package apus.persistence;

import apus.common.ApusSettings;
import apus.dao.impl.AbstractDAOImpl;
import apus.exception.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The
 * <code>SettingsManager</code> class used to work with system settings
 *
 * @author M. Vasilevsky
 */
public class SettingsManager {

    private final static Logger logger = Logger.getLogger(SettingsManager.class.getName());

    public static ApusSettings getSettings() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        Statement st = null;
        ResultSet rs = null;
        ApusSettings apusSettings = new ApusSettings();
        try {
            st = connection.createStatement();

            rs = st.executeQuery("select PVALUE from PARAMS where PKEY='PREFIX';");
            if (rs.next()) {
                apusSettings.setNumberPrefix(rs.getString("PVALUE").trim());
            }

            rs = st.executeQuery("select PVALUE from PARAMS where PKEY='NSIZE';");
            if (rs.next()) {
                apusSettings.setNumberSize(Integer.parseInt(rs.getString("PVALUE").trim()));
            }

            rs = st.executeQuery("select PVALUE from PARAMS where PKEY='TARIFF';");
            if (rs.next()) {
                apusSettings.setTariff(Integer.parseInt(rs.getString("PVALUE").trim()));
            }

            rs = st.executeQuery("select PVALUE from PARAMS where PKEY='DEF_LANG';");
            if (rs.next()) {
                apusSettings.setDefaultLanguage(rs.getString("PVALUE").trim());
            }

            rs = st.executeQuery("select PVALUE from PARAMS where PKEY='QUICK_SEARCH';");
            if (rs.next()) {
                apusSettings.setQuickSearch((rs.getString("PVALUE").trim().equals("1")) ? true : false);
            }

            connection.commit();
            return apusSettings;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception while reading settings from DB: {0}", e.getMessage());
            throw new DatabaseException("Can't read settings", e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                System.err.println("Can't close statement");
                ex.printStackTrace(System.err);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    public static void setSettings(ApusSettings apusSettings) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            logger.info("Saving settings to database..");

            st = connection.prepareStatement("UPDATE PARAMS SET PVALUE=? WHERE PKEY=?");

            st.setString(1, apusSettings.getNumberPrefix());
            st.setString(2, "PREFIX");
            st.execute();

            st.setString(1, Integer.toString(apusSettings.getNumberSize()));
            st.setString(2, "NSIZE");
            st.execute();

            st.setString(1, Integer.toString(apusSettings.getTariff()));
            st.setString(2, "TARIFF");
            st.execute();

            st.setString(1, apusSettings.getDefaultLanguage());
            st.setString(2, "DEF_LANG");
            st.execute();
            
            st.setBoolean(1, apusSettings.getQuickSearch());
            st.setString(2, "QUICK_SEARCH");
            st.execute();

            connection.commit();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception while saving settings to DB: {0}", e.getMessage());
            throw new DatabaseException("Can't change settings", e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                System.err.println("Can't close statement");
                ex.printStackTrace(System.err);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }
}
