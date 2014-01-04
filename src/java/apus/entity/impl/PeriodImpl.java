package apus.entity.impl;

import apus.entity.Period;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 * The
 * <code>PeriodImpl</code> class represents periods of the time. To specify the
 * time period used by the two objects of class a Date.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 * @see java.text.DateFormat
 */
public class PeriodImpl extends AbstractEntityImpl implements Period {

    /**
     * Start period of time.
     */
    private Date startDate;
    /**
     * End period of time.
     */
    private Date endDate;

    public PeriodImpl(int id) {
        super(id);
    }

    /**
     * Initializes a newly created {@code RealPeriod} object with an indication
     * of start period. Date in format like: "12.12.2012". {@code endDate}
     * initialize as today
     *
     * @param startDate - The start of period
     * @throws ParseException If the {@code startDate} argument has incorrect
     * format for parsing
     */
    public PeriodImpl(String startDate) throws ParseException {
        DateFormat df = DateFormat.getDateInstance();
        this.startDate = df.parse(startDate);
        this.endDate = new Date();
    }

    /**
     * Initializes a newly created {@code RealPeriod} object with an indication
     * of two dates. Date in format like: "12.12.2012".
     *
     * @param startDate The start of period.
     * @param endDate The end of period.
     * @throws ParseException If the {@code startDate} or {@code endDate}
     * arguments has incorrect format for parsing.
     */
    public PeriodImpl(String startDate, String endDate) throws ParseException {
        DateFormat df = DateFormat.getDateInstance();
        this.startDate = df.parse(startDate);
        this.endDate = df.parse(endDate);
    }

    public PeriodImpl(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Return the date of the end of the period.
     *
     * @return The date.
     */
    @Override
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Return the date of the start of the period.
     *
     * @return The date.
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return "from " + startDate + " to " + endDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PeriodImpl other = (PeriodImpl) obj;
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        return true;
    }
}
