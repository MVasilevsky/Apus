package apus.entity.impl;

import apus.entity.Person;

/**
 * The <code>PersonImpl</code> class represents physical person (extends 
 * {@code RealSubscriber}).
 * @author  Maxim Vasilevsky
 * @author  Roman Dyatkovsky
 * @see RealSubscriber
 */
public class PersonImpl extends SubscriberImpl implements Person {

    /** Number of the passport. */
    private String passportNumber;
    
    /**
     * Default constructor.
     */
    public PersonImpl() {
    }
    
    /**
     * Initializes a newly created {@code RealPerson} object with an indication 
     * of name.
     * @param name RealPerson name .
     */
    public PersonImpl(String name) {
        super(name);
    }
    
    public PersonImpl(int id){
        super(id);
    }

    /**
     * Initializes a newly created {@code Organization} object with an indication 
     * of passport number, name, address and list of phone numbers.
     * @param passportNumber Passport number.
     * @param name Name of person.
     * @param address Address of person.
     * @param phoneNumbers List of phone numbers, which belong to person.
     */   
    public PersonImpl(String passportNumber, String name, String address) {
        super(name, address);
        this.passportNumber = passportNumber;
    }

    /**
     * Gets passport number.
     * @return passport number.
     */
    @Override
    public String getPassportNumber() {
        return passportNumber;
    }

    /**
     * Sets passport number.
     * @param passportNumber passport number.
     */
    @Override
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public String getSubscriberType() {
        return "person";
    }
}
