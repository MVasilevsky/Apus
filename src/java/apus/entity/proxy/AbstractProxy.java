package apus.entity.proxy;

import apus.entity.AbstractEntity;
import apus.service.Service;
import apus.service.ServiceFactory;

/**
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 */
public abstract class AbstractProxy<TypeEn extends AbstractEntity, TypeService extends Service> {

    protected int id;
    protected TypeEn proxyObject;
    private Class<TypeService> clazz;

    public AbstractProxy(Class<TypeService> clazz) {
        this.clazz = clazz;
    }

    public TypeEn getProxy() {
        if (proxyObject == null) {
            proxyObject = (TypeEn) ServiceFactory.getService(clazz).read(id);
        }
        return proxyObject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        if (proxyObject != null) {
            proxyObject.setId(id);
        }
    }
}