package apus.service.impl;

import apus.dao.UserDAO;
import apus.entity.Subscriber;
import apus.entity.User;
import apus.exception.DatabaseException;
import apus.persistence.Transaction;
import apus.service.UserService;
import java.util.List;

/**
 * @author max
 */
public class UserServiceImpl extends ServiceImpl<User> implements UserService{

    public UserServiceImpl(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void create(User entity) throws DatabaseException {
        getTransaction().createDao(UserDAO.class).create(entity);
    }

    @Override
    public User read(Integer id) throws DatabaseException {
        return getTransaction().createDao(UserDAO.class).read(id);
    }

    @Override
    public void update(User entity) throws DatabaseException {
        getTransaction().createDao(UserDAO.class).update(entity);
    }

    @Override
    public void remove(User entity) throws DatabaseException {
        getTransaction().createDao(UserDAO.class).delete(entity);
    }

    @Override
    public List<User> findAll() throws DatabaseException {
        return getTransaction().createDao(UserDAO.class).findAll();
    }

    @Override
    public void removeUser(Subscriber subscriber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changePassword(String login, String newPassword) {
        getTransaction().createDao(UserDAO.class).changePassword(login, newPassword);
    }

    @Override
    public int checkAuthorisation(String login, String password) {
        return getTransaction().createDao(UserDAO.class).checkAuthorisation(login, password);
    }

    @Override
    public boolean isActionAllowed(User user, String action) {
        return getTransaction().createDao(UserDAO.class).isActionAllowed(user, action);
    }

    @Override
    public String getSubscriberName(User user) {
        return getTransaction().createDao(UserDAO.class).getSubscriberName(user);
    }

    @Override
    public List<User> findAllPersonal() {
        return getTransaction().createDao(UserDAO.class).findAllPersonal();
    }

    @Override
    public User findByLogin(String login) {
        return getTransaction().createDao(UserDAO.class).findByLogin(login);
    }

    @Override
    public boolean isActionExists(String action) {
        return getTransaction().createDao(UserDAO.class).isActionExists(action);
    }
    
}
