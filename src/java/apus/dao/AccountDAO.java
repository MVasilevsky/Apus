package apus.dao;

import apus.entity.Account;
import apus.entity.Period;
import apus.entity.Subscriber;
import java.util.List;

/**
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 */
public interface AccountDAO extends AbstractDAO<Account> {

    List<Account> readBySubscriber(Integer id);

    Account findBySubscriberAndPeriod(Subscriber subscriber, Period period);

    List<Account> findBySubscriber(String parameter);

    void payAccount(Integer id);
}
