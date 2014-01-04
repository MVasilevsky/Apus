package apus.service.impl;

import apus.dao.PhoneNumberDAO;
import apus.entity.PhoneNumber;
import apus.exception.DatabaseException;
import apus.persistence.Transaction;
import apus.service.PhoneNumberService;
import java.util.List;

/**
 * @author max
 */
public class PhoneNumberServiceImpl extends ServiceImpl<PhoneNumber> implements PhoneNumberService{

    public PhoneNumberServiceImpl(Transaction transaction) {
        super(transaction);
    }
    
    @Override
    public void create(PhoneNumber entity) throws DatabaseException {
        getTransaction().createDao(PhoneNumberDAO.class).create(entity);
    }

    @Override
    public PhoneNumber read(Integer id) throws DatabaseException {
        return getTransaction().createDao(PhoneNumberDAO.class).read(id);
    }

    @Override
    public void update(PhoneNumber entity) throws DatabaseException {
        getTransaction().createDao(PhoneNumberDAO.class).update(entity);
    }

    @Override
    public void remove(PhoneNumber entity) throws DatabaseException {
        getTransaction().createDao(PhoneNumberDAO.class).delete(entity);
    }

    @Override
    public List<PhoneNumber> findAll() throws DatabaseException {
        return getTransaction().createDao(PhoneNumberDAO.class).findAll();
    }

    @Override
    public PhoneNumber readByNumber(String number) {
        return getTransaction().createDao(PhoneNumberDAO.class).readByNumber(number);
    }

    @Override
    public List<PhoneNumber> findBySubscriber(String name) {
        return getTransaction().createDao(PhoneNumberDAO.class).findBySubscriber(name);
    }
    
}
