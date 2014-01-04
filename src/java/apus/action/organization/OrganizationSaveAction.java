package apus.action.organization;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.Organization;
import apus.entity.impl.OrganizationImpl;
import apus.service.OrganizationService;
import apus.service.ServiceFactory;
import apus.service.impl.OrganizationServiceImpl;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>OrganizationSaveAction</code> class represents action, used to save
 * organization information into the database
 *
 * @author M. Vasilevsky
 */
public class OrganizationSaveAction implements Action {

    @Override
    public String getName() {
        return "organization.save";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }

        if ((request.getParameter("id") == null) || (request.getParameter("id").isEmpty())
                || (request.getParameter("name") == null) || (request.getParameter("name").isEmpty())
                || (request.getParameter("bank") == null) || (request.getParameter("bank").isEmpty())
                || (request.getParameter("address") == null) || (request.getParameter("address").isEmpty())) {
            request.getSession().setAttribute("fail", res.getString("wrong.data"));
            return ActionResult.redirect("organization.list");
        }

        // if Save button have been pressed
        if (request.getParameter("saveButton") != null) {
            OrganizationService organizationService = ServiceFactory.getService(OrganizationService.class);
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Organization organization = (id == 0) ? new OrganizationImpl() : organizationService.read(id);

                // load data from request
                organization.setName(request.getParameter("name"));
                organization.setBankingDetails(request.getParameter("bank"));
                organization.setAddress(request.getParameter("address"));

                if (id != 0) {
                    organizationService.update(organization); // update
                    request.getSession().setAttribute("win", organization.getName() + " " + res.getString("updated"));
                } else {
                    organizationService.create(organization); // create
                    request.getSession().setAttribute("win", organization.getName() + " " + res.getString("added"));
                }
            } catch (Exception e) {
                request.getSession().setAttribute("fail", e.getMessage());
            }
            organizationService.close();
        } else {
            request.getSession().setAttribute("fail", res.getString("organization.not.saved"));
        }
        
        return ActionResult.redirect("organization.list");
    }
}
