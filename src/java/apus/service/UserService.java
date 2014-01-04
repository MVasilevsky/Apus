package apus.service;

import apus.entity.Subscriber;
import apus.entity.User;
import java.util.List;

/**
 * @author max
 */
public interface UserService extends Service<User>{
    
    void removeUser (Subscriber subscriber);
    
    void changePassword(String login, String newPassword);
    
    int checkAuthorisation(String login, String password);
    
    boolean isActionAllowed(User user, String action);
    
    String getSubscriberName(User user);
    
    List<User> findAllPersonal ();
    
    User findByLogin(String login);
    
    boolean isActionExists(String action);

}
