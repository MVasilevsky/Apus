package apus.entity.proxy;

import apus.entity.Call;
import apus.entity.PhoneNumber;
import apus.entity.Subscriber;
import apus.service.PhoneNumberService;
import java.util.List;

/**
 * The
 * <code>PhoneNumberProxy</code> class represents APUS phone number-proxy.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 * @see AbstractProxy
 * @see PhoneNumber
 */
public class PhoneNumberProxy extends AbstractProxy<PhoneNumber, PhoneNumberService> implements PhoneNumber {

    public PhoneNumberProxy() {
        super(PhoneNumberService.class);
    }

    @Override
    public int getCost() {
        return getProxy().getCost();
    }

    @Override
    public List<Call> getInCalls() {
        return getProxy().getInCalls();
    }

    @Override
    public String getNumber() {
        return getProxy().getNumber();
    }

    @Override
    public List<Call> getOutCalls() {
        return getProxy().getOutCalls();
    }

    @Override
    public Subscriber getOwner() {
        return getProxy().getOwner();
    }

    @Override
    public void setOwner(Subscriber owner) {
        getProxy().setOwner(owner);
    }

    @Override
    public void setNumber(String number) {
        getProxy().setNumber(number);
    }

    @Override
    public void addInCall(Call inCall) {
        getProxy().addInCall(inCall);
    }

    @Override
    public void addOutCall(Call outCall) {
        getProxy().addOutCall(outCall);
    }

    @Override
    public String toString() {
        return getProxy().toString();
    }
}