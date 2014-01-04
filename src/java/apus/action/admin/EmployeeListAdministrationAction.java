package apus.action.admin;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.User;
import apus.service.ServiceFactory;
import apus.service.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>EmployeeListAdministrationAction</code> class represents admin action,
 * used to view employee list
 *
 * @author M. Vasilevsky
 */
public class EmployeeListAdministrationAction implements Action {

    @Override
    public String getName() {
        return "employee";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = ServiceFactory.getService(UserService.class);
        List<User> employeeList = userService.findAllPersonal();
        request.setAttribute("employeeList", employeeList);
        userService.close();
        return ActionResult.forward("AdminView/EmployeeListView.jsp");
    }
}
