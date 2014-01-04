package apus.action.organization;

import apus.action.Action;
import apus.action.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>OrganizationCreateAction</code> class represents action, used to create
 * new organization
 *
 * @author M. Vasilevsky
 */
public class OrganizationCreateAction implements Action {

    @Override
    public String getName() {
        return "organization.create";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        return ActionResult.forward("OrganizationView/CreateOrganizationView.jsp");
    }
}
