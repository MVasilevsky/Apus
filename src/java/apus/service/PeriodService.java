package apus.service;

import apus.entity.Period;
import java.util.Date;

/**
 * @author max
 */
public interface PeriodService extends Service<Period>{
    Period readByStartAndEndDates(Date start, Date end);
}
