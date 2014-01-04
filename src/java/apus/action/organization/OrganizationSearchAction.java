package apus.action.organization;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.Organization;
import apus.exception.EntityNotFoundException;
import apus.service.OrganizationService;
import apus.service.ServiceFactory;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>OrganizationSearchAction</code> class represents action, used to search
 * organizations
 *
 * @author M. Vasilevsky
 */
public class OrganizationSearchAction implements Action {

    @Override
    public String getName() {
        return "organization.search";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<Organization> organizationList;
        OrganizationService organizationService = ServiceFactory.getService(OrganizationService.class);
        try {
            organizationList = organizationService.readByName(request.getParameter("name"));
        } catch (EntityNotFoundException e) {
            organizationList = new ArrayList<>();
        }
        request.setAttribute("organizationList", organizationList);
        organizationService.close();
        return ActionResult.forward("search/organizationSearch.jsp");
    }
}
