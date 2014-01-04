package apus.authorisation;

import apus.entity.User;
import apus.service.ServiceFactory;
import apus.service.UserService;

/**
 * The
 * <code>UserAuthorisationFactory</code> class creates UserAuthorisation objects
 * for every user
 * 
 * @see UserAuthorisation
 * @author M. Vasilevsky
 */
public class UserAuthorisationFactory {

    private static final int ADMIN = 1;
    private static final int CASHIER = 2;
    private static final int USER = 3;

    public static UserAuthorisation getAuthorizedUser(String login, int role) {

        UserAuthorisation authorisation = new UserAuthorisation();
        authorisation.setAutorised(true);
        authorisation.setLogin(login);

        UserService userService = ServiceFactory.getService(UserService.class);
        User user = userService.findByLogin(login);

        if (role == ADMIN) {
            authorisation.setRole(UserAuthorisation.Role.ADMIN);
            authorisation.setUsername(login);
        }
        if (role == CASHIER) {
            authorisation.setRole(UserAuthorisation.Role.CASHIER);
            authorisation.setUsername(login);
        }
        if (role == USER) {
            authorisation.setRole(UserAuthorisation.Role.USER);
            authorisation.setUsername(userService.getSubscriberName(user));
            authorisation.setSubscriberId(user.getSubscriber().getId());
        }

        return authorisation;
    }
}
