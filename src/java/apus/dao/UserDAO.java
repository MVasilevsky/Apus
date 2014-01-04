package apus.dao;

import apus.entity.Subscriber;
import apus.entity.User;
import java.util.List;

/**
 *
 * @author Max Vasilevsky
 */
public interface UserDAO extends AbstractDAO<User> {
    void addUserToSubscriber(Subscriber subscriber);
    void removeUser(Subscriber subscriber);
    void changePassword(String login, String newPassword);
    int checkAuthorisation(String login, String password);
    boolean isActionAllowed(User user, String action);
    String getSubscriberName(User user);
    List<User> findAllPersonal ();
    User findByLogin(String login);
    boolean isActionExists(String action);
}
