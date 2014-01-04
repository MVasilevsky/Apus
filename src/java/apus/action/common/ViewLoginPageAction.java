package apus.action.common;

import apus.action.Action;
import apus.action.ActionResult;
import apus.servlet.SessionServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>ViewLoginPageAction</code> class represents action, used to view login
 * page
 *
 * @author M. Vasilevsky
 */
public class ViewLoginPageAction implements Action {

    @Override
    public String getName() {
        return "login.view";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute(SessionServlet.USER_AUTH) == null) {
            return ActionResult.forward("loginPage.jsp");
        } else {
            return ActionResult.redirect("main.view");
        }
    }
}
