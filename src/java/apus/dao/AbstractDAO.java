package apus.dao;

import apus.entity.AbstractEntity;
import java.util.List;

/**
 * The
 * <code>AbstractDAO</code> interface represents common methods of every DAO.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 */
public interface AbstractDAO<EntityType extends AbstractEntity> {

    void create(EntityType entity);

    EntityType read(Integer id);

    void update(EntityType entity);

    void delete(EntityType entity);

    List<EntityType> findAll();
}
