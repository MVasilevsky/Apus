package apus.action.phonenumber;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.PhoneNumber;
import apus.exception.EntityNotFoundException;
import apus.persistence.SettingsManager;
import apus.service.PhoneNumberService;
import apus.service.ServiceFactory;
import apus.service.impl.PhoneNumberServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>PhoneNumberSearchAction</code> class represents action, used to search
 * phone numbers
 *
 * @author M. Vasilevsky
 */
public class PhoneNumberSearchAction implements Action {

    @Override
    public String getName() {
        return "phoneNumber.search";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<PhoneNumber> phoneNumberList;
        PhoneNumberService phoneNumberService = ServiceFactory.getService(PhoneNumberService.class);
        try {
            phoneNumberList = phoneNumberService.findBySubscriber(request.getParameter("name"));
        } catch (EntityNotFoundException e) {
            phoneNumberList = new ArrayList<>();
        } finally {
            phoneNumberService.close();
        }
        request.setAttribute("settings", SettingsManager.getSettings());
        request.setAttribute("phoneNumberList", phoneNumberList);
        return ActionResult.forward("search/phoneNumberSearch.jsp");
    }
}
