package apus.action.organization;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.impl.OrganizationImpl;
import apus.exception.EntityNotFoundException;
import apus.service.OrganizationService;
import apus.service.ServiceFactory;
import apus.service.impl.OrganizationServiceImpl;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>OrganizationDeleteAction</code> class represents action, used to delete
 * organization
 *
 * @author M. Vasilevsky
 */
public class OrganizationDeleteAction implements Action {

    @Override
    public String getName() {
        return "organization.delete";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
       OrganizationService organizationService = ServiceFactory.getService(OrganizationService.class);
        if ((request.getParameter("id") != null) && (request.getParameter("name") != null)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                organizationService.remove(new OrganizationImpl(id));
                request.getSession().setAttribute("win", request.getParameter("name") + " " + res.getString("removed"));
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("fail", res.getString("incorrect.id"));
            } catch (EntityNotFoundException e) {
                request.getSession().setAttribute("fail", res.getString("organization.not.found"));
            }
        } else {
            request.getSession().setAttribute("fail", res.getString("organization.not.removed"));
        }

        if (request.getParameter("search") != null) {
            request.getSession().setAttribute("search", request.getParameter("search"));
        }
        organizationService.close();
        return ActionResult.redirect("organization.list");
    }
}
