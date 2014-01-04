package apus.entity.proxy;

import apus.entity.Call;
import apus.entity.PhoneNumber;
import apus.service.CallService;
import java.util.Date;

/**
 * The
 * <code>CallProxy</code> class represents APUS call-proxy.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 * @see AbstractProxy
 */
public class CallProxy extends AbstractProxy<Call, CallService> implements Call {

    public CallProxy() {
        super(CallService.class);
    }

    @Override
    public Date getDateOfCall() {
        return getProxy().getDateOfCall();
    }

    @Override
    public int getDuration() {
        return getProxy().getDuration();
    }

    @Override
    public PhoneNumber getNumberFrom() {
        return getProxy().getNumberFrom();
    }

    @Override
    public PhoneNumber getNumberTo() {
        return getProxy().getNumberTo();
    }

    @Override
    public String toString() {
        return getProxy().toString();
    }
}