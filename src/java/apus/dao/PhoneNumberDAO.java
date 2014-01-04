package apus.dao;

import apus.entity.PhoneNumber;
import java.util.List;


/**
 * @author  Maxim Vasilevsky
 * @author  Roman Dyatkovsky
 */
public interface PhoneNumberDAO extends AbstractDAO <PhoneNumber>{
    PhoneNumber readByNumber (String number);
    List<PhoneNumber> findBySubscriber(String name);
}
