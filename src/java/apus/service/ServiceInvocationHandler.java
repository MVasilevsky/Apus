package apus.service;

import apus.service.impl.ServiceImpl;
import apus.exception.DatabaseException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Max
 */
public class ServiceInvocationHandler implements InvocationHandler {

    private ServiceImpl service;

    public ServiceInvocationHandler(Service service) {
        this.service = (ServiceImpl) service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
        try {
            Object result = method.invoke(service, arguments);
            service.getTransaction().commit();
            return result;
        } catch (DatabaseException e) {
            rollback(method);
            throw e;
        } catch (InvocationTargetException e) {
            rollback(method);
            throw e.getCause();
        }
    }

    private void rollback(Method method) {
        if (method.getDeclaringClass() != Service.class) {
            try {
                service.getTransaction().rollback();
            } catch (DatabaseException e) {
            }
        }
    }
}
