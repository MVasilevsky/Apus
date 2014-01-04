package apus.service.impl;

import apus.dao.OrganizationDAO;
import apus.dao.UserDAO;
import apus.entity.Organization;
import apus.entity.Person;
import apus.exception.DatabaseException;
import apus.persistence.Transaction;
import apus.service.OrganizationService;
import java.util.List;

/**
 * @author max
 */
public class OrganizationServiceImpl extends ServiceImpl<Organization> implements OrganizationService{

    public OrganizationServiceImpl(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void create(Organization entity) throws DatabaseException {
        getTransaction().createDao(OrganizationDAO.class).create(entity);
        getTransaction().createDao(UserDAO.class).addUserToSubscriber(entity);
    }

    @Override
    public Organization read(Integer id) throws DatabaseException {
        return getTransaction().createDao(OrganizationDAO.class).read(id);
    }

    @Override
    public void update(Organization entity) throws DatabaseException {
        getTransaction().createDao(OrganizationDAO.class).update(entity);
    }

    @Override
    public void remove(Organization entity) throws DatabaseException {
        getTransaction().createDao(OrganizationDAO.class).delete(entity);
        getTransaction().createDao(UserDAO.class).removeUser(entity);
    }

    @Override
    public List<Organization> findAll() throws DatabaseException {
        return getTransaction().createDao(OrganizationDAO.class).findAll();
    }

    @Override
    public List<Organization> readByName(String name) {
        return getTransaction().createDao(OrganizationDAO.class).readByName(name);
    }
    
}
