package apus.entity.proxy;

import apus.entity.Person;
import apus.entity.PhoneNumber;
import apus.service.PersonService;
import java.util.List;

/**
 * The
 * <code>PersonProxy</code> class represents APUS person-proxy.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 * @see AbstractProxy
 * @see Person
 */
public class PersonProxy extends AbstractProxy<Person, PersonService> implements Person {

    public PersonProxy() {
        super(PersonService.class);
    }

     @Override
    public String getAddress() {
        return getProxy().getAddress();
    }

    @Override
    public void setAddress(String address) {
        getProxy().setAddress(address);
    }

    @Override
    public String getName() {
        return getProxy().getName();
    }

    @Override
    public void setName(String name) {
        getProxy().setName(name);
    }

    @Override
    public List<PhoneNumber> getPhoneNumbers() {
        return getProxy().getPhoneNumbers();
    }

    @Override
    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        getProxy().setPhoneNumbers(phoneNumbers);
    }

    @Override
    public void addPhoneNumber(PhoneNumber number) {
        getProxy().addPhoneNumber(number);
    }

    @Override
    public String getPassportNumber() {
        return getProxy().getPassportNumber();
    }

    @Override
    public void setPassportNumber(String passportNumber) {
        getProxy().setPassportNumber(passportNumber);
    }

    @Override
    public String toString() {
        return getProxy().toString();
    }

    @Override
    public String getSubscriberType() {
        return "person";
    }
}