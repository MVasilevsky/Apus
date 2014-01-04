package apus.service;

import apus.entity.PhoneNumber;
import java.util.List;

/**
 * @author max
 */
public interface PhoneNumberService extends Service<PhoneNumber>{
    
    PhoneNumber readByNumber (String number);
    
    List<PhoneNumber> findBySubscriber(String name);

}
