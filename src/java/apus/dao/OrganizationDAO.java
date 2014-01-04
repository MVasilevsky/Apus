package apus.dao;

import apus.entity.Organization;
import java.util.List;

/**
 * @author  Maxim Vasilevsky
 * @author  Roman Dyatkovsky
 */
public interface OrganizationDAO extends SubscriberDAO<Organization> {
    public List<Organization> readByName(String name);
}