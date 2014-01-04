package apus.entity.impl;

import apus.entity.PhoneNumber;
import apus.entity.Subscriber;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The
 * <code>RealSubscriber</code> class represents APUS subscriber. It stores
 * information about the subscriber's name, his address and the list of phone
 * numbers.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 */
public abstract class SubscriberImpl extends AbstractEntityImpl implements Subscriber {

    /**
     * Subscriber name.
     */
    private String name;
    /**
     * Subscriber address.
     */
    private String address;
    /**
     * List of subscriber phone numbers.
     */
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    /**
     * Gets subscriber address.
     *
     * @return Subscriber address.
     */
    @Override
    public String getAddress() {
        return address;
    }

    /**
     * Sets subscriber address.
     *
     * @param address address.
     */
    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets subscriber name.
     *
     * @return subscriber name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets subscriber name.
     *
     * @param name Subscriber name.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets a list of subscriber phone numbers.
     *
     * @return List of subscriber phone numbers.
     */
    @Override
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     * Sets a list of phone numbers.
     *
     * @param phoneNumbers list of phone numbers.
     */
    @Override
    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public void addPhoneNumber(PhoneNumber number) {
        phoneNumbers.add(number);
    }

    /**
     * Default constructor.
     */
    public SubscriberImpl() {
    }

    public SubscriberImpl(int id) {
        super(id);
    }

    /**
     * Initializes a newly created {@code Subscriber} object with an indication
     * of name.
     *
     * @param name Name or title of subscriber
     */
    public SubscriberImpl(String name) {
        this.name = name;
    }

    /**
     * Initializes a newly created {@code Subscriber} object with an indication
     * of his name, address and list of phone numbers.
     *
     * @param name Subscriber name
     * @param address Subscriber address
     * @param phoneNumbers List of subscriber phone numbers
     */
    public SubscriberImpl(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SubscriberImpl other = (SubscriberImpl) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }

    @Override
    public abstract String getSubscriberType();
}
