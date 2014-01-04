package apus.service;

import apus.entity.AbstractEntity;
import apus.exception.DatabaseException;
import java.util.List;

/**
 * @author Max
 */
public interface Service<Entity extends AbstractEntity> {

    void create(Entity entity) throws DatabaseException;

    Entity read(Integer id) throws DatabaseException;

    void update(Entity entity) throws DatabaseException;

    void remove(Entity entity) throws DatabaseException;

    List<Entity> findAll() throws DatabaseException;

    void close();
    
}
