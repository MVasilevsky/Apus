package apus.action.common;

import apus.action.Action;
import apus.action.ActionResult;
import apus.authorisation.UserAuthorisation;
import apus.service.ServiceFactory;
import apus.service.UserService;
import apus.servlet.SessionServlet;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>PasswordSaveAction</code> class represents action, used change user
 * password
 *
 * @author M. Vasilevsky
 */
public class PasswordSaveAction implements Action {

    @Override
    public String getName() {
        return "password.save";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        UserService userService = ServiceFactory.getService(UserService.class);
        UserAuthorisation ua = (UserAuthorisation) request.getSession().getAttribute(SessionServlet.USER_AUTH);
        if ((request.getParameter("newPass") != null) && (request.getParameter("oldPass") != null)) {
            if (userService.checkAuthorisation(ua.getLogin(), request.getParameter("oldPass")) != -1) {
                request.getSession().setAttribute("win", res.getString("password.is.changed"));
                userService.changePassword(ua.getLogin(), request.getParameter("newPass"));
            } else {
                request.getSession().setAttribute("fail", res.getString("wrong.password"));
            }
        } else {
            request.getSession().setAttribute("fail", res.getString("wrong.data"));
        }
        userService.close();
        return ActionResult.redirect("settings");
    }
}
