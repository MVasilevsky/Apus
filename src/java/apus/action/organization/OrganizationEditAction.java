package apus.action.organization;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.Organization;
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
 * <code>OrganizationEditAction</code> class represents action, used to edit
 * organization information
 *
 * @author M. Vasilevsky
 */
public class OrganizationEditAction implements Action {

    @Override
    public String getName() {
        return "organization.edit";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        OrganizationService organizationService = ServiceFactory.getService(OrganizationService.class);
        if (request.getParameter("id") != null) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Organization organization = organizationService.read(id);
                request.setAttribute("organization", organization);
                return ActionResult.forward("OrganizationView/EditOrganizationView.jsp");
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("fail", res.getString("incorrect.id"));
            } catch (EntityNotFoundException e) {
                request.getSession().setAttribute("fail", res.getString("organization.not.found"));
            }
        }
        organizationService.close();
        return ActionResult.redirect("organization.list");
    }
}
