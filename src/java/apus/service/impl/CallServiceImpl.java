package apus.service.impl;

import apus.dao.CallDAO;
import apus.entity.Call;
import apus.entity.PhoneNumber;
import apus.exception.DatabaseException;
import apus.persistence.Transaction;
import apus.service.CallService;
import java.util.Date;
import java.util.List;

/**
 * @author max
 */
public class CallServiceImpl extends ServiceImpl<Call> implements CallService{

    public CallServiceImpl(Transaction transaction) {
        super(transaction);
    }

    @Override
    public Call readByNumbersAndTime(PhoneNumber out, PhoneNumber in, Date dateOfCall) {
        return getTransaction().createDao(CallDAO.class).readByNumbersAndTime(out, in, dateOfCall);
    }

    @Override
    public List<Call> readByNumber(int phoneNumberId) {
        return getTransaction().createDao(CallDAO.class).readByNumber(phoneNumberId);
    }

    @Override
    public void create(Call entity) throws DatabaseException {
        getTransaction().createDao(CallDAO.class).create(entity);
    }

    @Override
    public Call read(Integer id) throws DatabaseException {
        return getTransaction().createDao(CallDAO.class).read(id);
    }

    @Override
    public void update(Call entity) throws DatabaseException {
        getTransaction().createDao(CallDAO.class).update(entity);
    }

    @Override
    public void remove(Call entity) throws DatabaseException {
        getTransaction().createDao(CallDAO.class).delete(entity);
    }

    @Override
    public List<Call> findAll() throws DatabaseException {
        return getTransaction().createDao(CallDAO.class).findAll();
    }

}
