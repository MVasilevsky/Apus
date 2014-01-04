package apus.entity;

import java.util.Date;

/**
 * The
 * <code>Period</code> interface defines methods to Period.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 */
public interface Period extends AbstractEntity {

    public Date getEndDate();

    public Date getStartDate();
}