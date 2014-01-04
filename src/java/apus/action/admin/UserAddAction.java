package apus.action.admin;

import apus.action.Action;
import apus.action.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>UserAddAction</code> class represents admin action, used to add new
 * employee
 *
 * @author M. Vasilevsky
 */
public class UserAddAction implements Action {

    @Override
    public String getName() {
        return "user.create";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        return ActionResult.forward("AdminView/CreateEmployeeView.jsp");
    }
}
