package apus.service.impl;

import apus.dao.AccountDAO;
import apus.dao.OrganizationDAO;
import apus.dao.PeriodDAO;
import apus.dao.PersonDAO;
import apus.entity.Account;
import apus.entity.Organization;
import apus.entity.Period;
import apus.entity.Person;
import apus.entity.Subscriber;
import apus.entity.impl.AccountImpl;
import apus.entity.impl.PeriodImpl;
import apus.exception.DatabaseException;
import apus.exception.EntityNotFoundException;
import apus.persistence.Transaction;
import apus.service.AccountService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Max
 */
public class AccountServiceImpl extends ServiceImpl<Account> implements AccountService {

    public AccountServiceImpl(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void addMonthlyAccounts() throws DatabaseException {
        AccountDAO accountDAO = getTransaction().createDao(AccountDAO.class);
        PeriodDAO periodDAO = getTransaction().createDao(PeriodDAO.class);
        PersonDAO personDAO = getTransaction().createDao(PersonDAO.class);
        OrganizationDAO organizationDAO = getTransaction().createDao(OrganizationDAO.class);

        // Creating period from 1st number of previous month to 1st number of current month
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, -12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date end = calendar.getTime();
        calendar.add(Calendar.MONTH, -1);
        Date start = calendar.getTime();

        Period period;

        try {
            period = periodDAO.readByStartAndEndDates(start, end);
        } catch (EntityNotFoundException e) {
            period = new PeriodImpl(start, end);
            periodDAO.create(period);
        }

        for (Person per : personDAO.findAll()) {
            Account account = new AccountImpl(per, period);
            account.calculate();
            accountDAO.create(account);
        }

        for (Organization org : organizationDAO.findAll()) {
            Account account = new AccountImpl(org, period);
            account.calculate();
            accountDAO.create(account);
        }

    }

    @Override
    public List<Account> findAll() throws DatabaseException {
        return getTransaction().createDao(AccountDAO.class).findAll();
    }

    @Override
    public void remove(int id) {
        getTransaction().createDao(AccountDAO.class).delete(new AccountImpl(id));
    }

    @Override
    public void remove(Account account) {
        getTransaction().createDao(AccountDAO.class).delete(account);
    }

    @Override
    public void create(Account entity) {
        getTransaction().createDao(AccountDAO.class).create(entity);
    }

    @Override
    public Account read(Integer id) {
        return getTransaction().createDao(AccountDAO.class).read(id);
    }

    @Override
    public void update(Account entity) {
        getTransaction().createDao(AccountDAO.class).update(entity);
    }

    @Override
    public List<Account> findBySubscriber(String parameter) {
        return getTransaction().createDao(AccountDAO.class).findBySubscriber(parameter);
    }

    @Override
    public List<Account> readBySubscriber(Integer id) {
        return getTransaction().createDao(AccountDAO.class).readBySubscriber(id);
    }

    @Override
    public Account findBySubscriberAndPeriod(Subscriber subscriber, Period period) {
        return getTransaction().createDao(AccountDAO.class).findBySubscriberAndPeriod(subscriber, period);
    }

    @Override
    public void payAccount(Integer id) {
        getTransaction().createDao(AccountDAO.class).payAccount(id);
    }
}
