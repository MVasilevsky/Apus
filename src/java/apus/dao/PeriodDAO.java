package apus.dao;

import apus.entity.Period;
import java.util.Date;

/**
 * @author  Maxim Vasilevsky
 * @author  Roman Dyatkovsky
 */
public interface PeriodDAO extends AbstractDAO <Period> {
    Period readByStartAndEndDates(Date start, Date end);
}
