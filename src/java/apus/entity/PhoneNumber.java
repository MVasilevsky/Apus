package apus.entity;

import apus.persistence.SettingsManager;
import java.util.List;

/**
 * The
 * <code>PhoneNumber</code> interface defines methods to PhoneNumber.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 */
public interface PhoneNumber extends AbstractEntity {

    public static int DEFAULT_COST = SettingsManager.getSettings().getTariff();

    /**
     * Gets cost of calls on this phone number.
     *
     * @return cost of call.
     */
    public int getCost();

    /**
     * Gets list of calls from this phone number.
     *
     * @return List of in calls.
     */
    public List<Call> getInCalls();

    /**
     * Gets phone number.
     *
     * @return Phone number.
     */
    public String getNumber();

    /**
     * Gets list of calls to this phone number.
     *
     * @return List of out calls.
     */
    public List<Call> getOutCalls();

    /**
     * Gets owner of the phone number.
     *
     * @return Owner of the phone number.
     */
    public Subscriber getOwner();

    /**
     * Sets owner of the phone number.
     *
     * @param owner Owner of the phone number.
     */
    public void setOwner(Subscriber owner);

    /**
     * Sets phone number.
     *
     * @param Phone number.
     */
    public void setNumber(String number);

    /**
     * Adds incoming call.
     *
     * @param inCall Incoming call.
     */
    public void addInCall(Call inCall);

    /**
     * Adds outcoming call.
     *
     * @param outCall Outcoming call.
     */
    public void addOutCall(Call outCall);
}
