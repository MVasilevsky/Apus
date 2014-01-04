package apus.service.impl;

import apus.dao.PeriodDAO;
import apus.entity.Period;
import apus.exception.DatabaseException;
import apus.persistence.Transaction;
import apus.service.PeriodService;
import java.util.Date;
import java.util.List;

/**
 * @author max
 */
public class PeriodServiceImpl extends ServiceImpl<Period> implements PeriodService{

    public PeriodServiceImpl(Transaction transaction) {
        super(transaction);
    }
    
    @Override
    public void create(Period entity) throws DatabaseException {
        getTransaction().createDao(PeriodDAO.class).create(entity);
    }

    @Override
    public Period read(Integer id) throws DatabaseException {
        return getTransaction().createDao(PeriodDAO.class).read(id);
    }

    @Override
    public void update(Period entity) throws DatabaseException {
        getTransaction().createDao(PeriodDAO.class).update(entity);
    }

    @Override
    public void remove(Period entity) throws DatabaseException {
        getTransaction().createDao(PeriodDAO.class).delete(entity);
    }

    @Override
    public List<Period> findAll() throws DatabaseException {
        return getTransaction().createDao(PeriodDAO.class).findAll();
    }

    @Override
    public Period readByStartAndEndDates(Date start, Date end) {
        return getTransaction().createDao(PeriodDAO.class).readByStartAndEndDates(start, end);
    }
    
}
