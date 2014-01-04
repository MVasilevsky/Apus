package apus.service;

import apus.exception.DatabaseException;
import apus.persistence.Transaction;
import apus.persistence.TransactionFactory;
import apus.service.impl.AccountServiceImpl;
import apus.service.impl.CallServiceImpl;
import apus.service.impl.OrganizationServiceImpl;
import apus.service.impl.PeriodServiceImpl;
import apus.service.impl.PersonServiceImpl;
import apus.service.impl.PhoneNumberServiceImpl;
import apus.service.impl.ServiceImpl;
import apus.service.impl.UserServiceImpl;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Max
 */
public class ServiceFactory {

    private static final Map<Class<? extends Service>, Class<? extends ServiceImpl>> SERVICES = new ConcurrentHashMap<>();

    static {
        SERVICES.put(AccountService.class, AccountServiceImpl.class);
        SERVICES.put(CallService.class, CallServiceImpl.class);
        SERVICES.put(OrganizationService.class, OrganizationServiceImpl.class);
        SERVICES.put(PeriodService.class, PeriodServiceImpl.class);
        SERVICES.put(PersonService.class, PersonServiceImpl.class);
        SERVICES.put(PhoneNumberService.class, PhoneNumberServiceImpl.class);
        SERVICES.put(UserService.class, UserServiceImpl.class);
    }

    @SuppressWarnings("unchecked")
    public static <Type extends Service> Type getService(Class<Type> key) throws DatabaseException {
        Class<? extends Service> value = SERVICES.get(key);
        if (value != null) {
            try {
                ClassLoader classLoader = value.getClassLoader();
                Class<?>[] interfaces = {key};
                Transaction transaction = TransactionFactory.createSQLTransaction();
                Service service = value.getConstructor(Transaction.class).newInstance(transaction);
                InvocationHandler handler = new ServiceInvocationHandler(service);
                return (Type) Proxy.newProxyInstance(classLoader, interfaces, handler);
            } catch (DatabaseException e) {
                throw e;
            } catch (Exception e) {
            }
        }
        return null;
    }
}
