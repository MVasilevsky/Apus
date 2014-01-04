package apus.entity;

import java.text.DateFormat;
import java.util.Date;

/**
 * The
 * <code>Call</code> interface defines methods to Call.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 * @see java.text.DateFormat
 * @see apus.entity.PhoneNumber
 */
public interface Call extends AbstractEntity {

    public Date getDateOfCall();

    public int getDuration();

    public PhoneNumber getNumberFrom();

    public PhoneNumber getNumberTo();
}
