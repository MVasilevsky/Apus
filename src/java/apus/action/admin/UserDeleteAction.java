package apus.action.admin;

import apus.action.Action;
import apus.action.ActionResult;
import apus.authorisation.UserAuthorisation;
import apus.entity.User;
import apus.exception.DatabaseException;
import apus.service.ServiceFactory;
import apus.service.UserService;
import apus.service.impl.UserServiceImpl;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>UserDeleteAction</code> class represents admin action, used to remove
 * employee
 *
 * @author M. Vasilevsky
 */
public class UserDeleteAction implements Action {

    @Override
    public String getName() {
        return "user.delete";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        if (request.getParameter("id") != null) {
            UserService userService = ServiceFactory.getService(UserService.class);
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String userLogin = request.getParameter("login");
                String currentAdminLogin = ((UserAuthorisation) request.getSession().getAttribute("userAuth")).getLogin();
                if (!userLogin.equals(currentAdminLogin)) {
                    userService.remove(new User(id));
                    request.getSession().setAttribute("win", res.getString("user.is.removed"));
                } else {
                    request.getSession().setAttribute("fail", res.getString("remove.yourself"));
                }
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("fail", res.getString("incorrect.id"));
            } catch (DatabaseException e) {
                request.getSession().setAttribute("fail", res.getString("user.not.found"));
            } finally {
                userService.close();
            }
        } else {
            request.getSession().setAttribute("fail", res.getString("user.isn't.removed"));
        }
        return ActionResult.redirect("employee");
    }
}
