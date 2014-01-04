package apus.entity.impl;

import apus.entity.Account;
import apus.entity.Call;
import apus.entity.Period;
import apus.entity.PhoneNumber;
import apus.entity.Subscriber;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * The
 * <code>AccountImpl</code> class represents accounts, which is billed to the
 * subscriber over a period of time.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 * @see apus.entity.RealSubscriber
 * @see apus.entity.RealPeriod
 * @see java.math.BigDecimal
 */
public class AccountImpl extends AbstractEntityImpl implements Account {

    private Subscriber subscriber;
    private Period period;
    private int price;
    private boolean isPaid;

    /**
     * Default constructor.
     */
    public AccountImpl() {
    }

    public AccountImpl(int id) {
        super(id);
    }

    /**
     * Initializes a newly created {@code RealAccount} object with an indication
     * of the subscriber and the amount of time.
     *
     * @param subscriber RealSubscriber.
     * @param period RealPeriod of time.
     */
    public AccountImpl(Subscriber subscriber, Period period) {
        this.subscriber = subscriber;
        this.period = period;
        this.calculate();
    }

    /**
     * Return the period of time.
     *
     * @return The period.
     */
    @Override
    public Period getPeriod() {
        return period;
    }

    /**
     * Sets period of time.
     *
     * @param period RealPeriod of the time.
     */
    @Override
    public void setPeriod(Period period) {
        this.period = period;
    }

    /**
     * Return price of account.
     *
     * @return The price.
     */
    @Override
    public int getPrice() {
        return price;
    }

    /**
     * Sets price of account.
     *
     * @param price Price of account.
     */
    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Return the subscriber.
     *
     * @return The subscriber.
     */
    @Override
    public Subscriber getSubscriber() {
        return subscriber;
    }

    /**
     * Sets subscriber.
     *
     * @param subscriber RealSubscriber.
     */
    @Override
    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public final void calculate() {
        price = 0;
        for (PhoneNumber phonenumber : getSubscriber().getPhoneNumbers()) {
            for (Call call : phonenumber.getOutCalls()) {
                if (call.getDateOfCall().after(getPeriod().getStartDate()) && call.getDateOfCall().before(getPeriod().getEndDate())) {
                    price += phonenumber.getCost() * call.getDuration();
                }
            }
        }
    }

    @Override
    public boolean isPaid() {
        return isPaid;
    }

    @Override
    public void setPay(boolean pay) {
        this.isPaid = pay;
    }

    @Override
    public String toString() {
        return "account of the " + getSubscriber() + " on the period " + getPeriod() + ". Price = " + getPrice();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccountImpl other = (AccountImpl) obj;
        if (!Objects.equals(this.getSubscriber(), other.getSubscriber())) {
            return false;
        }
        if (!Objects.equals(this.getPeriod(), other.getPeriod())) {
            return false;
        }
        return true;
    }
}