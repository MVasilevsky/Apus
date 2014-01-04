package apus.action.phonenumber;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.Subscriber;
import apus.exception.EntityNotFoundException;
import apus.persistence.SettingsManager;
import apus.service.OrganizationService;
import apus.service.PersonService;
import apus.service.ServiceFactory;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>PhoneNumberCreateAction</code> class represents action, used to add new
 * phone number to selected subscriber
 *
 * @author M. Vasilevsky
 */
public class PhoneNumberCreateAction implements Action {

    @Override
    public String getName() {
        return "phoneNumber.create";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        try {
            int sub_id = Integer.parseInt(request.getParameter("sub_id"));
            int subscriberType = Integer.parseInt(request.getParameter("sub_type"));
            Subscriber sub;
            if (subscriberType == 0) {
             PersonService personService = ServiceFactory.getService(PersonService.class);
                    sub = personService.read(sub_id);
                    personService.close();
            } else {
                OrganizationService organizationService = ServiceFactory.getService(OrganizationService.class);
                    sub = organizationService.read(sub_id);
                    organizationService.close();
            }
            request.setAttribute("settings", SettingsManager.getSettings());
            request.setAttribute("subscriber", sub);
            request.setAttribute("subscriberType", subscriberType);
            return ActionResult.forward("PhoneNumberView/CreatePhoneNumberView.jsp");
        } catch (NumberFormatException e) {
            request.setAttribute("fail", res.getString("incorrect.id"));
        } catch (EntityNotFoundException e) {
            request.setAttribute("fail", res.getString("subscriber.not.found"));
        }
        return ActionResult.forward("PhoneNumberView/PhoneNumberListView.jsp");
    }
}
