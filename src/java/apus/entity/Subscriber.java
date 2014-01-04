package apus.entity;

import java.util.List;

/**
 * The
 * <code>Subscriber</code> interface defines methods to Subscriber.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 */
public interface Subscriber extends AbstractEntity {

    public String getAddress();

    public void setAddress(String address);

    public String getName();

    public void setName(String name);

    public List<PhoneNumber> getPhoneNumbers();

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers);

    public void addPhoneNumber(PhoneNumber number);

    public String getSubscriberType();
}