package apus.dao.impl;

import apus.entity.Subscriber;
import apus.entity.User;
import apus.entity.proxy.OrganizationProxy;
import apus.entity.proxy.PersonProxy;
import apus.exception.DatabaseException;
import apus.dao.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author M.Vasilevsky
 */
public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO {

    public UserDAOImpl(Connection connection) {
        super(connection);
    }
    
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());

    @Override
    public void create(User entity) {
        entity.setId(getNextUser());

        PreparedStatement st = null;
        try {
            // subscriber
            if (entity.getSubscriber() != null) {
                st = connection.prepareStatement("INSERT INTO USERS (ID_USER, ROLE_ID, LOGIN, PASSWORD, SUBSCRIBER_ID) VALUES (?, ?, ?, ?, ?);");
                st.setInt(5, entity.getSubscriber().getId());
            } else { //employee
                st = connection.prepareStatement("INSERT INTO USERS (ID_USER, ROLE_ID, LOGIN, PASSWORD) VALUES (?, ?, ?, ?);");
            }

            st.setInt(1, entity.getId());
            st.setInt(2, entity.getRole());
            st.setString(3, entity.getLogin());
            st.setString(4, entity.getPassword());

            st.execute();
            
            logger.log(Level.INFO, "New user was added:{0}, id={1}", new Object[]{entity.getLogin(), entity.getId()});
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception while creating new user: {0}", e.getMessage());
            throw new DatabaseException("Can't create new user", e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public void delete(User entity) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("delete from USERS where ID_USER=?;");
            st.setInt(1, entity.getId());
            st.execute();
            logger.log(Level.INFO, "User with id={0} was removed", entity.getId());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception while removing user: {0}", e.getMessage());
            throw new DatabaseException("Can't delete user with ID=" + entity.getId(), e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public void changePassword(String login, String newPassword) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("UPDATE USERS set PASSWORD=? where LOGIN=?;");
            st.setString(1, newPassword);
            st.setString(2, login);
            st.execute();
            logger.log(Level.INFO, "Password of {0} was changed", login);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception while changing password of {0}: {1}", new Object[]{login, e.getMessage()});
            throw new DatabaseException("Can't change password of " + login, e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public int checkAuthorisation(String login, String password) {
        Statement st = null;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM USERS WHERE LOGIN='" + login + "' AND PASSWORD='" + password + "';");

            if (rs.next()) {
                return rs.getInt("ROLE_ID");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception while checking authorisation of {0}: {1}", new Object[]{login, ex.getMessage()});
            throw new DatabaseException("Can't check authorisation", ex);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
            }
        }
        return -1;
    }

    @Override
    public boolean isActionAllowed(User user, String action) {
        Statement st = null;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM ROLE_ACTION"
                    + " WHERE ROLE_ID=(SELECT ROLE_ID FROM USERS WHERE LOGIN='" + user.getLogin() + "')"
                    + " AND ACTION_ID=(SELECT ID_ACTION FROM ACTIONS WHERE NAME_ACTION='" + action + "');");

            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception while checking is action allowed: {0}", ex.getMessage());
            throw new DatabaseException("Can't check authorisation", ex);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
            }
        }

    }

    @Override
    public String getSubscriberName(User user) {

        PreparedStatement st = null;
        ResultSet rs;
        try {
            st = connection.prepareStatement("SELECT SUBSCRIBER_NAME FROM SUBSCRIBER "
                    + "JOIN USERS ON SUBSCRIBER_ID=ID_SUBSCRIBER AND LOGIN=?;");
            st.setString(1, user.getLogin());
            rs = st.executeQuery();
            rs.next();
            return rs.getString("SUBSCRIBER_NAME").trim();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception while reading subscriber name: {0}", e.getMessage());
            throw new DatabaseException("Can't read subscriber name by user", e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public List<User> findAllPersonal() {
        Statement st = null;
        ResultSet rs;
        User employee;
        List<User> employeeList = new ArrayList<>();
        try {
            st = connection.createStatement();

            // admins + ..
            rs = st.executeQuery("SELECT ID_USER, ROLE_ID, LOGIN FROM USERS "
                    + "WHERE ROLE_ID=1;");

            while (rs.next()) {
                employee = new User(rs.getInt("ID_USER"));
                employee.setRole(rs.getInt("ROLE_ID"));
                employee.setLogin(rs.getString("LOGIN").trim());
                employeeList.add(employee);
            }

            // .. + cashiers
            rs = st.executeQuery("SELECT ID_USER, ROLE_ID, LOGIN FROM USERS "
                    + "WHERE ROLE_ID=2;");

            while (rs.next()) {
                employee = new User(rs.getInt("ID_USER"));
                employee.setRole(rs.getInt("ROLE_ID"));
                employee.setLogin(rs.getString("LOGIN").trim());
                employeeList.add(employee);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception while reading employee list: {0}", e.getMessage());
            throw new DatabaseException("Can't read employee list", e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
            }
        }
        return employeeList;
    }

    @Override
    public User findByLogin(String login) {
        PreparedStatement st = null;
        PreparedStatement st2 = null;
        ResultSet rs;
        ResultSet rs2;
        int tp;
        User employee = null;
        try {
            st = connection.prepareStatement("SELECT ID_USER, SUBSCRIBER_ID, ROLE_ID, PASSWORD FROM USERS "
                    + "WHERE LOGIN=?;");
            st.setString(1, login.trim());
            rs = st.executeQuery();

            if (rs.next()) {
                st2 = connection.prepareStatement("SELECT SUBSCRIBER_TYPE FROM SUBSCRIBER "
                        + "WHERE ID_SUBSCRIBER=?;");
                st2.setInt(1, rs.getInt("SUBSCRIBER_ID"));

                rs2 = st2.executeQuery();
                if (rs2.next()) {
                    tp = rs2.getInt("SUBSCRIBER_TYPE");
                    employee = new User(rs.getInt("ID_USER"));
                    Subscriber sub;

                    if (tp == 0) {
                        sub = new PersonProxy();
                        sub.setId(rs.getInt("SUBSCRIBER_ID"));
                    } else {
                        sub = new OrganizationProxy();
                        sub.setId(rs.getInt("SUBSCRIBER_ID"));
                    }

                    employee.setSubscriber(sub);
                    employee.setRole(rs.getInt("ROLE_ID"));
                    employee.setLogin(login);
                    employee.setPassword(rs.getString("PASSWORD"));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Can't read employee with login " + login.trim(), e);
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
        return employee;
    }

    @Override
    public boolean isActionExists(String action) {
        Statement st = null;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM ACTIONS WHERE NAME_ACTION='" + action + "');");

            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            throw new DatabaseException("Can't check authorisation", ex);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public User read(Integer id) {
        PreparedStatement st = null;
        PreparedStatement st2 = null;
        ResultSet rs;
        ResultSet rs2;
        int tp;
        User employee = null;
        try {
            st = connection.prepareStatement("SELECT ID_USER, SUBSCRIBER_ID, ROLE_ID, LOGIN, PASSWORD FROM USERS "
                    + "WHERE ID_USER=?;");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                st2 = connection.prepareStatement("SELECT SUBSCRIBER_TYPE FROM SUBSCRIBER "
                        + "WHERE ID_SUBSCRIBER=?;");
                st2.setInt(1, rs.getInt("SUBSCRIBER_ID"));

                rs2 = st2.executeQuery();
                if (rs2.next()) {
                    tp = rs2.getInt("SUBSCRIBER_TYPE");
                    employee = new User(rs.getInt("ID_USER"));
                    Subscriber sub;

                    if (tp == 0) {
                        sub = new PersonProxy();
                        sub.setId(rs.getInt("SUBSCRIBER_ID"));
                    } else {
                        sub = new OrganizationProxy();
                        sub.setId(rs.getInt("SUBSCRIBER_ID"));
                    }

                    employee.setSubscriber(sub);
                    employee.setRole(rs.getInt("ROLE_ID"));
                    employee.setLogin(rs.getString("LOGIN"));
                    employee.setPassword(rs.getString("PASSWORD"));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Can't read employee", e);
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
        return employee;
    }

    @Override
    public void update(User entity) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("UPDATE USERS set PASSWORD=?, LOGIN=?, SUBSCRIBER_ID=?, ROLE_ID=?  where ID_USER=?;");
            st.setString(1, entity.getPassword());
            st.setString(2, entity.getLogin());
            st.setInt(3, entity.getSubscriber().getId());
            st.setInt(4, entity.getRole());
            st.setInt(5, entity.getId());
            st.execute();
            logger.log(Level.INFO, "User was changed");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception while user updating");
            throw new DatabaseException("Can't update user", e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public List<User> findAll() {
        Statement st = null;
        ResultSet rs;
        User employee;
        List<User> employeeList = new ArrayList<>();
        try {
            st = connection.createStatement();

            rs = st.executeQuery("SELECT ID_USER, ROLE_ID, LOGIN FROM USERS");

            while (rs.next()) {
                employee = new User(rs.getInt("ID_USER"));
                employee.setRole(rs.getInt("ROLE_ID"));
                employee.setLogin(rs.getString("LOGIN").trim());
                employeeList.add(employee);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception while reading employee list: {0}", e.getMessage());
            throw new DatabaseException("Can't read employee list", e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
            }
        }
        return employeeList;
    }
    
    @Override
    public void addUserToSubscriber(Subscriber subscriber) {
        PreparedStatement st = null;
        try {
            Formatter f = new Formatter();

            st = connection.prepareStatement("insert into USERS"
                        + " (ID_USER, SUBSCRIBER_ID, ROLE_ID,"
                        + " LOGIN, PASSWORD) values (?,?,?,?,?)");
            st.setInt(1, getNextUser());
            st.setInt(2, subscriber.getId());
            st.setInt(3, 3);
            st.setString(4, f.format("%08d", subscriber.getId()).toString());
            st.setString(5, "123456");
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Can't create new user");
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e){
            }
        }
        
    }

    @Override
    public void removeUser(Subscriber subscriber) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("delete from USERS where SUBSCRIBER_ID=?;");
            st.setInt(1, subscriber.getId());
            st.execute();
            logger.log(Level.INFO, "User of subscriber with id={0} was removed", subscriber.getId());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception while removing user: {0}", e.getMessage());
            throw new DatabaseException("Can't delete user of subscriber with ID=" + subscriber.getId(), e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
            }
        }
    }
    
}
