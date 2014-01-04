package apus.action.cashier;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.PhoneNumber;
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
 * <code>CashierSearchAction</code> class represents cashier action, used to
 * search subscribers
 *
 * @author M. Vasilevsky
 */
public class CashierSearchAction implements Action {

    @Override
    public String getName() {
        return "cashier.search";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        PhoneNumberService phoneNumberService = ServiceFactory.getService(PhoneNumberService.class);
        List<PhoneNumber> phoneNumberList;
        try {
            phoneNumberList = phoneNumberService.findBySubscriber(request.getParameter("name"));
        } catch (Exception e) {
            request.setAttribute("fail", res.getString("database.error"));
            phoneNumberList = phoneNumberService.findAll();
        }
        request.setAttribute("settings", SettingsManager.getSettings());
        request.setAttribute("phoneNumberList", phoneNumberList);
        phoneNumberService.close();
        return ActionResult.forward("search/cashierSearch.jsp");
        }
}
