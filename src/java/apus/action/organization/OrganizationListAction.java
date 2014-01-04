package apus.action.organization;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.Organization;
import apus.persistence.SettingsManager;
import apus.service.OrganizationService;
import apus.service.ServiceFactory;
import apus.service.impl.OrganizationServiceImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>OrganizationListAction</code> class represents action, used to view
 * organizations list
 *
 * @author M. Vasilevsky
 */
public class OrganizationListAction implements Action {

    @Override
    public String getName() {
        return "organization.list";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<Organization> organizationList;
        OrganizationService organizationService = ServiceFactory.getService(OrganizationService.class);
        if (request.getSession().getAttribute("search") == null) {
            organizationList = organizationService.findAll();
        } else {
            organizationList = organizationService.readByName((String) request.getSession().getAttribute("search"));
        }
        request.setAttribute("organizationList", organizationList);
        request.setAttribute("sets", SettingsManager.getSettings());
        organizationService.close();
        return ActionResult.forward("OrganizationView/OrganizationListView.jsp");
    }
}
