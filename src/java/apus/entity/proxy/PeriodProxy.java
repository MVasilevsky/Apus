package apus.entity.proxy;

import apus.entity.Period;
import apus.service.PeriodService;
import java.text.DateFormat;
import java.util.Date;

/**
 * The
 * <code>PeriodProxy</code> class represents APUS period-proxy.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 * @see java.text.DateFormat
 * @see AbstractProxy
 */
public class PeriodProxy extends AbstractProxy<Period, PeriodService> implements Period {

    public PeriodProxy() {
        super(PeriodService.class);
    }

    @Override
    public Date getEndDate() {
        return getProxy().getEndDate();
    }

    @Override
    public Date getStartDate() {
        return getProxy().getStartDate();
    }

    @Override
    public String toString() {
        return getProxy().toString();
    }
}