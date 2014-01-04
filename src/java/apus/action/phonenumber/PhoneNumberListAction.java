package apus.action.phonenumber;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.PhoneNumber;
import apus.persistence.SettingsManager;
import apus.service.PhoneNumberService;
import apus.service.ServiceFactory;
import apus.service.impl.PhoneNumberServiceImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>PhoneNumberListAction</code> class represents action, used to view PN
 * list
 *
 * @author M. Vasilevsky
 */
public class PhoneNumberListAction implements Action {

    @Override
    public String getName() {
        return "phoneNumber.list";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<PhoneNumber> phoneNumberList;
        PhoneNumberService phoneNumberService = ServiceFactory.getService(PhoneNumberService.class);
        if (request.getSession().getAttribute("search") == null) {
            phoneNumberList = phoneNumberService.findAll();
        } else {
            phoneNumberList = phoneNumberService.findBySubscriber((String) request.getSession().getAttribute("search"));
        }
        request.setAttribute("settings", SettingsManager.getSettings());
        request.setAttribute("phoneNumberList", phoneNumberList);
        phoneNumberService.close();
        return ActionResult.forward("PhoneNumberView/PhoneNumberListView.jsp");
    }
}