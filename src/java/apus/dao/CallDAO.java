package apus.dao;

import apus.entity.Call;
import apus.entity.PhoneNumber;
import java.util.Date;
import java.util.List;

/**
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 */
public interface CallDAO extends AbstractDAO<Call> {

    Call readByNumbersAndTime(PhoneNumber out, PhoneNumber in, Date dateOfCall);

    List<Call> readByNumber(int phoneNumberId);
}
