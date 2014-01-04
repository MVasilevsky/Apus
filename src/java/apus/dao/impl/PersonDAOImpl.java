package apus.dao.impl;

import apus.entity.Person;
import apus.entity.PhoneNumber;
import apus.entity.proxy.PhoneNumberProxy;
import apus.entity.impl.PersonImpl;
import apus.exception.DatabaseException;
import apus.dao.PersonDAO;
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
public class PersonDAOImpl extends SubscriberDAOImpl<Person> implements PersonDAO {

    public PersonDAOImpl(Connection connection) {
        super(connection);
    }
    
    private static final Logger logger = Logger.getLogger(PersonDAOImpl.class.getName());

    @Override
    public List<Person> findAll() {
        Statement st = null;
        PreparedStatement st2 = null;
        ResultSet rs;
        ResultSet rs2;
        List<Person> subscriberList = new ArrayList<>();
        try {
            st = connection.createStatement();
            st2 = connection.prepareStatement("select ID_PHONE_NUMBER from PHONE_NUMBER where SUBSCRIBER_ID=?");
            rs = st.executeQuery("select * from SUBSCRIBER where SUBSCRIBER_TYPE=0");

            while (rs.next()) {
                Person subscriber;
                subscriber = new PersonImpl(rs.getString("PASSPORT_NUMBER").trim(),
                        rs.getString("SUBSCRIBER_NAME").trim(), rs.getString("ADDRESS").trim());

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
            logger.log(Level.SEVERE, "Exception while reading person list: {0}", ex.getMessage());
            throw new DatabaseException("Can't read person list", ex);
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
    public List<Person> readByName(String name) {
        return super.readByName(name, 0); 
    }
    
}
