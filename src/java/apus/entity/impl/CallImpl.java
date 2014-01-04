package apus.entity.impl;

import apus.entity.Call;
import apus.entity.PhoneNumber;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 * The
 * <code>CallImpl</code> class represents calls. The call is given by the
 * caller's number and the number receiving the call.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 * @see java.text.DateFormat
 * @see apus.entity.PhoneNumber
 */
public class CallImpl extends AbstractEntityImpl implements Call {

    /**
     * Number of the calling telephone number.
     */
    private PhoneNumber numberFrom;
    /**
     * Number of the called telephone number.
     */
    private PhoneNumber numberTo;
    /**
     * Duration of call.
     */
    private int duration;
    /**
     * Beginning of the call
     */
    private Date dateOfCall;

    public CallImpl(int id) {
        super(id);
    }

    /**
     * Initializes a newly created {@code Call} object with an indication of two
     * number.
     *
     * @param numberFrom calling number.
     * @param numberTo called number.
     */
    public CallImpl(PhoneNumber numberFrom, PhoneNumber numberTo) {
        this.numberFrom = numberFrom;
        this.numberTo = numberTo;
    }

    public CallImpl() {
    }

    public CallImpl(PhoneNumber numberFrom, PhoneNumber numberTo, Date dateOfCall) {
        this.numberFrom = numberFrom;
        this.numberTo = numberTo;
        this.dateOfCall = dateOfCall;
    }

    public CallImpl(PhoneNumber numberFrom, PhoneNumber numberTo, int duration, Date dateOfCall) {
        this.numberFrom = numberFrom;
        this.numberTo = numberTo;
        this.duration = duration;
        this.dateOfCall = dateOfCall;
    }

    public CallImpl(PhoneNumber numberFrom, PhoneNumber numberTo, int duration, String dateOfCall) throws ParseException {
        this.numberFrom = numberFrom;
        this.numberTo = numberTo;
        this.duration = duration;
        DateFormat df = DateFormat.getDateInstance();
        this.dateOfCall = df.parse(dateOfCall);
    }

    @Override
    public Date getDateOfCall() {
        return dateOfCall;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public PhoneNumber getNumberFrom() {
        return numberFrom;
    }

    @Override
    public PhoneNumber getNumberTo() {
        return numberTo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CallImpl other = (CallImpl) obj;
        if (!Objects.equals(this.numberFrom, other.numberFrom)) {
            return false;
        }
        if (!Objects.equals(this.numberTo, other.numberTo)) {
            return false;
        }
        if (!Objects.equals(this.dateOfCall, other.dateOfCall)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Call from " + numberFrom + " to " + numberTo + ". Date of call: " + dateOfCall;
    }
}