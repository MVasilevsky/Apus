/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apus.authorisation;

/**
 * The
 * <code>UserAuthorisation</code> class represents information about authorized
 * user.
 * For every user there is one object of this class in session.
 * @author M. Vasilevsky
 */
public class UserAuthorisation {

    public enum Role {
        ADMIN,
        CASHIER,
        USER
    }
    private String login;
    private String username;
    private int subscriberId;
    private Role role;
    private boolean autorised;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAutorised() {
        return autorised;
    }

    public void setAutorised(boolean autorised) {
        this.autorised = autorised;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }
}
