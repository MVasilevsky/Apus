package apus.authorisation;

import apus.service.ServiceFactory;
import apus.service.UserService;

/**
 * The
 * <code>UserValidator</code> class uses to check user authorisation
 *
 * @author M. Vasilevsky
 */
public class UserValidator {

    public static int isLoginValid(String login, String password) {
        return ServiceFactory.getService(UserService.class).checkAuthorisation(login, password);
    }
}
