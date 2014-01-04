package apus.service;

import apus.entity.Call;
import apus.entity.PhoneNumber;
import java.util.Date;
import java.util.List;

/**
 * @author max
 */
public interface CallService extends Service<Call>{

    Call readByNumbersAndTime(PhoneNumber out, PhoneNumber in, Date dateOfCall);

    List<Call> readByNumber(int phoneNumberId);
    
}
