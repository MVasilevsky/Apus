package apus.action.phonenumber;

import apus.action.Action;
import apus.action.ActionResult;
import apus.authorisation.UserAuthorisation;
import apus.entity.Call;
import apus.entity.PhoneNumber;
import apus.exception.EntityNotFoundException;
import apus.persistence.SettingsManager;
import apus.service.PhoneNumberService;
import apus.service.ServiceFactory;
import apus.service.impl.PhoneNumberServiceImpl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>CallsSpecialListAction</code> class represents action, used to view
 * lists of calls on chosen period
 *
 * @author M. Vasilevsky
 */
public class CallsSpecialListAction implements Action {

    @Override
    public String getName() {
        return "call.list.period";
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

                    int month = Integer.parseInt(request.getParameter("month"));
                    int year = Integer.parseInt(request.getParameter("year"));

                    List<Call> outCalls = new ArrayList<Call>();
                    List<Call> inCalls = new ArrayList<Call>();

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);


                    Date start = calendar.getTime();

                    calendar.add(Calendar.MONTH, 1);

                    Date end = calendar.getTime();

                    for (Call call : phoneNumber.getOutCalls()) {
                        if ((call.getDateOfCall().after(start)) && (call.getDateOfCall().before(end))) {
                            outCalls.add(call);
                        }
                    }

                    for (Call call : phoneNumber.getInCalls()) {
                        if ((call.getDateOfCall().after(start)) && (call.getDateOfCall().before(end))) {
                            inCalls.add(call);
                        }
                    }

                    request.setAttribute("settings", SettingsManager.getSettings());
                    request.setAttribute("outCalls", outCalls);
                    request.setAttribute("inCalls", inCalls);
                    request.setAttribute("phoneNumber", phoneNumber);
                    request.setAttribute("filter", "true");
                    request.setAttribute("month", month);
                    request.setAttribute("year", year);

                    return ActionResult.forward("PhoneNumberView/PhoneNumberView.jsp");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("fail", res.getString("incorrect.id"));
            } catch (EntityNotFoundException e) {
                request.setAttribute("fail", res.getString("number.not.found"));
            } catch (Exception e) {
                request.setAttribute("fail", res.getString("error.occured"));
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
