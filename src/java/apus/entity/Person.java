package apus.entity;

/**
 * The
 * <code>Person</code> interface defines methods to Person.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 * @see Subscriber
 */
public interface Person extends Subscriber {

    public String getPassportNumber();

    public void setPassportNumber(String passportNumber);
}