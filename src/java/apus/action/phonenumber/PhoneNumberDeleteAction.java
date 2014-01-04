package apus.action.phonenumber;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.impl.PhoneNumberImpl;
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
 * <code>PhoneNumberDeleteAction</code> class represents action, used to delete
 * phone number from database
 *
 * @author M. Vasilevsky
 */
public class PhoneNumberDeleteAction implements Action {

    @Override
    public String getName() {
        return "phoneNumber.delete";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        if ((request.getParameter("id") != null) && (request.getParameter("number") != null)) {
            PhoneNumberService phoneNumberService = ServiceFactory.getService(PhoneNumberService.class);
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                phoneNumberService.remove(new PhoneNumberImpl(id));
                request.getSession().setAttribute("win", SettingsManager.getSettings().getNumberPrefix() + request.getParameter("number") + " " + res.getString("removed"));
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("fail", res.getString("incorrect.id"));
            } catch (EntityNotFoundException e) {
                request.getSession().setAttribute("fail", e.getMessage());
            } finally {
                phoneNumberService.close();
            }
        } else {
            request.getSession().setAttribute("fail", res.getString("number.not.removed"));
        }

        if (request.getParameter("search") != null) {
            request.getSession().setAttribute("search", request.getParameter("search"));
        }

        return ActionResult.redirect("phoneNumber.list");
    }
}