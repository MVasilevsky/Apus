package apus.service.impl;

import apus.dao.PersonDAO;
import apus.dao.UserDAO;
import apus.entity.Person;
import apus.exception.DatabaseException;
import apus.persistence.Transaction;
import apus.service.PersonService;
import java.util.List;

/**
 * @author max
 */
public class PersonServiceImpl extends ServiceImpl<Person> implements PersonService{

    public PersonServiceImpl(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void create(Person entity) throws DatabaseException {
        getTransaction().createDao(PersonDAO.class).create(entity);
        getTransaction().createDao(UserDAO.class).addUserToSubscriber(entity);
    }

    @Override
    public Person read(Integer id) throws DatabaseException {
        return getTransaction().createDao(PersonDAO.class).read(id);
    }

    @Override
    public void update(Person entity) throws DatabaseException {
        getTransaction().createDao(PersonDAO.class).update(entity);
    }

    @Override
    public void remove(Person entity) throws DatabaseException {
        getTransaction().createDao(PersonDAO.class).delete(entity);
        getTransaction().createDao(UserDAO.class).removeUser(entity);
    }

    @Override
    public List<Person> findAll() throws DatabaseException {
        return getTransaction().createDao(PersonDAO.class).findAll();
    }

    @Override
    public List<Person> readByName(String name) {
        return getTransaction().createDao(PersonDAO.class).readByName(name);
    }
    
}
