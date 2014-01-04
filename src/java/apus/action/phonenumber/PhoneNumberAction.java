package apus.action.phonenumber;

import apus.action.Action;
import apus.action.ActionResult;
import apus.authorisation.UserAuthorisation;
import apus.entity.PhoneNumber;
import apus.exception.EntityNotFoundException;
import apus.persistence.SettingsManager;
import apus.service.PhoneNumberService;
import apus.service.ServiceFactory;
import apus.service.impl.PhoneNumberServiceImpl;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>DefaultAction</code> class represents action, used to view phone number
 * information
 *
 * @author M. Vasilevsky
 */
public class PhoneNumberAction implements Action {

    @Override
    public String getName() {
        return "phoneNumber.view";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        UserAuthorisation requesterAuthorisation = (UserAuthorisation) request.getSession().getAttribute("userAuth");
        UserAuthorisation.Role requesterRole = requesterAuthorisation.getRole();
        PhoneNumberService phoneNumberService = ServiceFactory.getService(PhoneNumberService.class);

        if (request.getParameter("id") != null) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                PhoneNumber phoneNumber = phoneNumberService.read(id);
                int phone_sub_id = phoneNumber.getOwner().getId();

                if (((requesterRole == UserAuthorisation.Role.USER) && (requesterAuthorisation.getSubscriberId() == phone_sub_id)) || requesterRole == UserAuthorisation.Role.ADMIN) {
                    request.setAttribute("settings", SettingsManager.getSettings());
                    request.setAttribute("outCalls", phoneNumber.getOutCalls());
                    request.setAttribute("inCalls", phoneNumber.getInCalls());
                    request.setAttribute("phoneNumber", phoneNumber);
                    return ActionResult.forward("PhoneNumberView/PhoneNumberView.jsp");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("fail", res.getString("incorrect.id"));
            } catch (EntityNotFoundException e) {
                request.setAttribute("fail", res.getString("number.not.found"));
            } finally {
                phoneNumberService.close();
            }
            return ActionResult.redirect("main.view");
        } else {
            if (requesterRole == UserAuthorisation.Role.USER) {
                phoneNumberService.close();
                return ActionResult.redirect("main.view");
            } else {
                List<PhoneNumber> phoneNumberList = phoneNumberService.findAll();
                request.setAttribute("fail", res.getString("number.not.found"));
                request.setAttribute("phoneNumberList", phoneNumberList);
                request.setAttribute("settings", SettingsManager.getSettings());
                phoneNumberService.close();
                return ActionResult.forward("PhoneNumberView/PhoneNumberListView.jsp");
            }
        }
    }
}
