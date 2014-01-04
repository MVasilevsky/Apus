package apus.service;

import apus.entity.Account;
import apus.entity.Period;
import apus.entity.Subscriber;
import apus.exception.DatabaseException;
import java.util.List;

/**
 * @author Max
 */
public interface AccountService extends Service<Account> {
    
    public void remove(int id) throws DatabaseException;

    public List<Account> findBySubscriber(String parameter) throws DatabaseException;

    public List<Account> readBySubscriber(Integer id) throws DatabaseException;

    public Account findBySubscriberAndPeriod(Subscriber subscriber, Period period) throws DatabaseException;

    public void payAccount(Integer id) throws DatabaseException;

    public void addMonthlyAccounts() throws DatabaseException;
}
