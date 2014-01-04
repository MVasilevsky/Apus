package apus.entity;

/**
 * The
 * <code>RealOrganization</code> interface defines methods to Organization.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 */
public interface Organization extends Subscriber {

    public String getBankingDetails();

    public void setBankingDetails(String bankingDetails);
}