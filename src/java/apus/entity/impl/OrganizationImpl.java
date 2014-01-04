package apus.entity.impl;

import apus.entity.Organization;

/**
 * The
 * <code>OrganizationImpl</code> class represents legal person (extends
 * {@code RealSubscriber}).
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 * @see RealSubscriber
 */
public class OrganizationImpl extends SubscriberImpl implements Organization {

    private String bankingDetails;

    public OrganizationImpl(String name) {
        super(name);
    }

    public OrganizationImpl(int id) {
        super(id);
    }

    public OrganizationImpl() {
    }

    public OrganizationImpl(String name, String address) {
        super(name, address);
    }

    @Override
    public String getBankingDetails() {
        return bankingDetails;
    }

    @Override
    public void setBankingDetails(String bankingDetails) {
        this.bankingDetails = bankingDetails;
    }

    @Override
    public String getSubscriberType() {
        return "organization";
    }
}
