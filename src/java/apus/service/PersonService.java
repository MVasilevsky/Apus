package apus.service;

import apus.entity.Person;
import java.util.List;

/**
 * @author max
 */
public interface PersonService extends Service<Person>{
    public List<Person> readByName(String name);
}
