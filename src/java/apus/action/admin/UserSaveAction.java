package apus.action.admin;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.User;
import apus.exception.DatabaseException;
import apus.service.ServiceFactory;
import apus.service.UserService;
import apus.service.impl.UserServiceImpl;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>UserSaveAction</code> class represents admin action, used to save
 * employee information
 *
 * @author M. Vasilevsky
 */
public class UserSaveAction implements Action {

    @Override
    public String getName() {
        return "user.save";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }

        // if Save button have been pressed
        if (request.getParameter("saveButton") != null) {

            User user = new User();

            // load data from request
            if (request.getParameter("login") != null) {
                user.setLogin(request.getParameter("login"));
            }

            if (request.getParameter("pass") != null) {
                user.setPassword(request.getParameter("pass"));
            }

            if (request.getParameter("role") != null) {
                user.setRole(Integer.parseInt(request.getParameter("role")));
            }
            UserService userService = ServiceFactory.getService(UserService.class);
            try {
                userService.create(user);
                request.getSession().setAttribute("win", res.getString("new.employee.added"));
            } catch (DatabaseException e) {
                request.getSession().setAttribute("fail", res.getString("incorrect.user.data"));
                return ActionResult.redirect("user.create");
            } finally {
                userService.close();
            }
        }
        return ActionResult.redirect("employee");
    }
}
