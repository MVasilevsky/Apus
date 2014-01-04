package apus.action.organization;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.Organization;
import apus.exception.EntityNotFoundException;
import apus.persistence.SettingsManager;
import apus.service.OrganizationService;
import apus.service.ServiceFactory;
import apus.service.impl.OrganizationServiceImpl;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>OrganizationAction</code> class represents action, used to view
 * organization information
 *
 * @author M. Vasilevsky
 */
public class OrganizationAction implements Action {

    @Override
    public String getName() {
        return "organization.view";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        OrganizationService organizationService = ServiceFactory.getService(OrganizationService.class);
        if ((request.getParameter("id") != null) || (request.getSession().getAttribute("id") != null)) {
            try {
                int id = (request.getParameter("id") != null) ? (Integer.parseInt(request.getParameter("id"))) : ((Integer) request.getSession().getAttribute("id"));
                Organization organization = organizationService.read(id);
                request.setAttribute("organization", organization);
                request.setAttribute("settings", SettingsManager.getSettings());
                organizationService.close();
                return ActionResult.forward("OrganizationView/OrganizationView.jsp");
            } catch (NumberFormatException e) {
                request.setAttribute("fail", res.getString("incorrect.id"));
            } catch (EntityNotFoundException e) {
                request.setAttribute("fail", res.getString("organization.not.found"));
            }
        }
        List<Organization> organizationList = organizationService.findAll();
        request.setAttribute("organizationList", organizationList);
        organizationService.close();
        return ActionResult.forward("OrganizationView/OrganizationListView.jsp");
    }
}
