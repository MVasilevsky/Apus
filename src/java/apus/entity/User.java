package apus.entity;

import apus.entity.impl.AbstractEntityImpl;

/**
 * @author Max Vasilevsky
 */
public class User extends AbstractEntityImpl {

    private Subscriber subscriber;
    private int role;
    private String login;
    private String password;

    public User() {
    }

    public User(int id) {
        super(id);
    }

    public User(Subscriber subscriber, int role, String login, String password) {
        this.subscriber = subscriber;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public User(Subscriber subscriber, int role, String login, String password, int id) {
        super(id);
        this.subscriber = subscriber;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
