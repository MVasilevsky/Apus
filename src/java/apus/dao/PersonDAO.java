/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apus.dao;

import apus.entity.Person;
import java.util.List;

/**
 * @author  Maxim Vasilevsky
 * @author  Roman Dyatkovsky
 * @since APUS v0.2
 */
public interface PersonDAO extends SubscriberDAO<Person> {
    public List<Person> readByName(String name);
}
