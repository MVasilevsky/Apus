package apus.entity.proxy;

import apus.entity.Organization;
import apus.entity.PhoneNumber;
import apus.service.OrganizationService;
import java.util.List;

/**
 * The
 * <code>OrganizationProxy</code> class represents APUS organization-proxy.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 * @see AbstractProxy
 */
public class OrganizationProxy extends AbstractProxy<Organization, OrganizationService> implements Organization {

    public OrganizationProxy() {
        super(OrganizationService.class);
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
    public String getBankingDetails() {
        return getProxy().getBankingDetails();
    }

    @Override
    public void setBankingDetails(String bankingDetails) {
        getProxy().setBankingDetails(bankingDetails);
    }

    @Override
    public String toString() {
        return getProxy().toString();
    }

    @Override
    public String getSubscriberType() {
        return "organization";
    }
}