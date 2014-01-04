package apus.action.phonenumber;

import apus.action.Action;
import apus.action.ActionResult;
import apus.exception.EntityNotFoundException;
import apus.persistence.SettingsManager;
import apus.service.PhoneNumberService;
import apus.service.ServiceFactory;
import apus.service.impl.PhoneNumberServiceImpl;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>PhoneNumberEditAction</code> class represents action, used to edit PN
 * information
 *
 * @author M. Vasilevsky
 */
public class PhoneNumberEditAction implements Action {

    @Override
    public String getName() {
        return "phoneNumber.edit";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        if (request.getParameter("id") != null) {
            PhoneNumberService phoneNumberService = ServiceFactory.getService(PhoneNumberService.class);
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("phoneNumber", phoneNumberService.read(id));
                request.setAttribute("settings", SettingsManager.getSettings());
                return ActionResult.forward("PhoneNumberView/EditPhoneNumberView.jsp");
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("fail", res.getString("incorrect.id"));
            } catch (EntityNotFoundException e) {
                request.getSession().setAttribute("fail", res.getString("subscriber.not.found"));
            } finally {
                phoneNumberService.close();
            }
        }

        return ActionResult.redirect("phoneNumber.list");

    }
}