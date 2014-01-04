package apus.action.call;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.Call;
import apus.exception.DatabaseException;
import apus.persistence.SettingsManager;
import apus.service.CallService;
import apus.service.ServiceFactory;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>CallListAction</code> class represents action, used to view call list
 *
 * @author M. Vasilevsky
 */
public class CallListAction implements Action {

    @Override
    public String getName() {
        return "call.list";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        List<Call> callList;
        CallService callService = ServiceFactory.getService(CallService.class);
        if (request.getParameter("num_id") != null) {
            try {
                callList = callService.readByNumber(Integer.parseInt(request.getParameter("num_id")));
            } catch (NumberFormatException e) {
                request.setAttribute("fail", res.getString("incorrect.id"));
                callList = callService.findAll();
            } catch (DatabaseException e) {
                request.setAttribute("fail", res.getString("number.not.found"));
                callList = callService.findAll();
            }
        } else {
            callList = callService.findAll();
        }
        request.setAttribute("settings", SettingsManager.getSettings());
        request.setAttribute("callList", callList);
        callService.close();
        return ActionResult.forward("CallView/CallListView.jsp");
    }
}
