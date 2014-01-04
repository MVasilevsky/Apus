package apus.entity.proxy;

import apus.entity.Account;
import apus.entity.Period;
import apus.entity.Subscriber;
import apus.service.AccountService;
import java.math.BigDecimal;

/**
 * The
 * <code>AccountProxy</code> class represents accounts-proxy.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 * @see AbstractProxy
 * @see java.math.BigDecimal
 */
public class AccountProxy extends AbstractProxy<Account, AccountService> implements Account {

    public AccountProxy() {
        super(AccountService.class);
    }

    @Override
    public Period getPeriod() {
        return getProxy().getPeriod();
    }

    @Override
    public int getPrice() {
        return getProxy().getPrice();
    }

    @Override
    public void setPrice(int price) {
        getProxy().setPrice(price);
    }

    @Override
    public Subscriber getSubscriber() {
        return getProxy().getSubscriber();
    }

    @Override
    public void setPeriod(Period period) {
        getProxy().setPeriod(period);
    }

    @Override
    public void setSubscriber(Subscriber subscriber) {
        getProxy().getSubscriber();
    }

    @Override
    public void calculate() {
        getProxy().calculate();
    }

    @Override
    public String toString() {
        return getProxy().toString();
    }

    @Override
    public boolean isPaid() {
        return getProxy().isPaid();
    }

    @Override
    public void setPay(boolean pay) {
        getProxy().setPay(pay);
    }
}