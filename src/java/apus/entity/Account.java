package apus.entity;

/**
 * The
 * <code>Account</code> interface defines methods to Account.
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 */
public interface Account extends AbstractEntity {

    public Period getPeriod();
    public void setPeriod(Period period);
    public int getPrice();
    public void setPrice(int price);
    public Subscriber getSubscriber();
    public void setSubscriber(Subscriber subscriber);
    public boolean isPaid();
    public void setPay(boolean pay);
    public void calculate();
    
}