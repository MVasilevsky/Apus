package apus.service;

import apus.entity.Organization;
import java.util.List;

/**
 * @author max
 */
public interface OrganizationService extends Service<Organization>{
    public List<Organization> readByName(String name);
}
