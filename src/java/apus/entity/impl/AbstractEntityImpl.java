package apus.entity.impl;

import apus.entity.AbstractEntity;

/**
 * The
 * <code>AbstractEntityImpl</code> class represents base entity, which
 * implements base methods.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 * @see AbstractEntity
 */
public abstract class AbstractEntityImpl implements AbstractEntity {

    protected int id;

    public AbstractEntityImpl() {
    }

    public AbstractEntityImpl(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
