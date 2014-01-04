package apus.dao.impl;

import apus.entity.Organization;
import apus.entity.PhoneNumber;
import apus.entity.proxy.PhoneNumberProxy;
import apus.entity.impl.OrganizationImpl;
import apus.exception.DatabaseException;
import apus.dao.OrganizationDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Roman Dyatkovsky
 * @author Maxim Vasilevsky
 */
public class OrganizationDAOImpl extends SubscriberDAOImpl<Organization> implements OrganizationDAO {

    public OrganizationDAOImpl(Connection connection) {
        super(connection);
    }
    
    private final static Logger logger = Logger.getLogger(OrganizationDAOImpl.class.getName());

    @Override
    public List<Organization> findAll() {
        Statement st = null;
        PreparedStatement st2 = null;
        ResultSet rs;
        ResultSet rs2;
        List<Organization> subscriberList = new ArrayList<>();
        try {
            st = connection.createStatement();
            st2 = connection.prepareStatement("select ID_PHONE_NUMBER from PHONE_NUMBER where SUBSCRIBER_ID=?");
            rs = st.executeQuery("select * from SUBSCRIBER where SUBSCRIBER_TYPE=1");

            while (rs.next()) {
                Organization subscriber;

                subscriber = new OrganizationImpl(rs.getString("SUBSCRIBER_NAME").trim(), rs.getString("ADDRESS").trim());
                subscriber.setBankingDetails(rs.getString("BANKING_DETAILS").trim());
                subscriber.setId(rs.getInt("ID_SUBSCRIBER"));

                st2.setInt(1, rs.getInt("ID_SUBSCRIBER"));
                rs2 = st2.executeQuery();

                while (rs2.next()) {
                    PhoneNumber pn = new PhoneNumberProxy();
                    pn.setId(rs2.getInt("ID_PHONE_NUMBER"));
                    subscriber.addPhoneNumber(pn);
                }
                subscriberList.add(subscriber);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception while reading organization list: {0}", ex.getMessage());
            throw new DatabaseException("Can't read organization list", ex);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (st2 != null) {
                    st2.close();
                }
            } catch (SQLException ex) {
            }
        }
        return subscriberList;
    }

    @Override
    public List<Organization> readByName(String name) {
        return super.readByName(name, 1);
    }
    
    
    
}
