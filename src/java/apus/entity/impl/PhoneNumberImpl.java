package apus.entity.impl;

import apus.entity.Call;
import apus.entity.PhoneNumber;
import apus.entity.Subscriber;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The
 * <code>PhoneNumberImpl</code> class represents phone number.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 */
public class PhoneNumberImpl extends AbstractEntityImpl implements PhoneNumber {

    /**
     * The owner of number
     */
    private Subscriber owner;
    /**
     * Phone number in international format.
     */
    private String number;
    /**
     * List of out calls
     */
    private List<Call> outCalls = new ArrayList<>();
    /**
     * List of in calls
     */
    private List<Call> inCalls = new ArrayList<>();

    /**
     * Default constructor.
     */
    public PhoneNumberImpl() {
    }

    public PhoneNumberImpl(int id) {
        super(id);
    }

    /**
     * Initializes a newly created {@code PhoneNumber} object with an indication
     * of number.
     *
     * @param number Phone number.
     */
    public PhoneNumberImpl(String number) {
        this.number = number;
    }

    /**
     * Initializes a newly created {@code PhoneNumber} object with an indication
     * of number and owner.
     *
     * @param owner Owner of the phone number.
     * @param number Phone number.
     */
    public PhoneNumberImpl(Subscriber owner, String number) {
        this.owner = owner;
        this.number = number;
    }

    /**
     * Gets cost of calls on this phone number.
     *
     * @return cost of call.
     */
    @Override
    public int getCost() {
        return DEFAULT_COST;
    }

    /**
     * Gets list of calls from this phone number.
     *
     * @return List of in calls.
     */
    @Override
    public List<Call> getInCalls() {
        return inCalls;
    }

    /**
     * Gets phone number.
     *
     * @return Phone number.
     */
    @Override
    public String getNumber() {
        return number;
    }

    /**
     * Gets list of calls to this phone number.
     *
     * @return List of out calls.
     */
    @Override
    public List<Call> getOutCalls() {
        return outCalls;
    }

    /**
     * Gets owner of the phone number.
     *
     * @return Owner of the phone number.
     */
    @Override
    public Subscriber getOwner() {
        return owner;
    }

    /**
     * Sets owner of the phone number.
     *
     * @param owner Owner of the phone number.
     */
    @Override
    public void setOwner(Subscriber owner) {
        this.owner = owner;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Adds incoming call.
     *
     * @param inCall Incoming call.
     */
    @Override
    public void addInCall(Call inCall) {
        this.inCalls.add(inCall);
    }

    /**
     * Adds outcoming call.
     *
     * @param outCall Outcoming call.
     */
    @Override
    public void addOutCall(Call outCall) {
        this.outCalls.add(outCall);
    }

    @Override
    public String toString() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhoneNumberImpl other = (PhoneNumberImpl) obj;
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        return true;
    }
}
