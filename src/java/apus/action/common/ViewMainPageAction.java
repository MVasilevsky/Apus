package apus.action.common;

import apus.action.Action;
import apus.action.ActionResult;
import apus.authorisation.UserAuthorisation;
import apus.entity.Account;
import apus.entity.User;
import apus.persistence.DataBaseStatistics;
import apus.persistence.SettingsManager;
import apus.service.AccountService;
import apus.service.OrganizationService;
import apus.service.PersonService;
import apus.service.PhoneNumberService;
import apus.service.ServiceFactory;
import apus.service.UserService;
import apus.service.impl.AccountServiceImpl;
import apus.service.impl.OrganizationServiceImpl;
import apus.service.impl.PersonServiceImpl;
import apus.service.impl.PhoneNumberServiceImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>ViewMainPageAction</code> class represents action, used to view main
 * page
 *
 * @author M. Vasilevsky
 */
public class ViewMainPageAction implements Action {

    @Override
    public String getName() {
        return "main.view";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        UserAuthorisation ua = (UserAuthorisation) request.getSession().getAttribute("userAuth");
        UserAuthorisation.Role role = ua.getRole();

        if (role.equals(UserAuthorisation.Role.ADMIN)) {
            request.setAttribute("statistics", DataBaseStatistics.getStatistics());
            return ActionResult.forward("mainAdmin.jsp");
        }

        if (role.equals(UserAuthorisation.Role.CASHIER)) {
            PhoneNumberService phoneNumberService = ServiceFactory.getService(PhoneNumberService.class);
            request.setAttribute("settings", SettingsManager.getSettings());
            request.setAttribute("phoneNumberList", phoneNumberService.findAll());
            phoneNumberService.close();
            return ActionResult.forward("mainCashier.jsp");
        }

        if (role.equals(UserAuthorisation.Role.USER)) {
            int sub_id = ua.getSubscriberId();
            User user = ServiceFactory.getService(UserService.class).findByLogin(ua.getLogin());
            if (user.getSubscriber().getSubscriberType().equals("person")) {
                PersonService personService = ServiceFactory.getService(PersonService.class);
                request.setAttribute("person", personService.read(user.getSubscriber().getId()));
                personService.close();
            } else {
                OrganizationService organizationService = ServiceFactory.getService(OrganizationService.class);
                    request.setAttribute("organization", organizationService.read(user.getSubscriber().getId()));
                organizationService.close();
            }

            AccountService accountService = ServiceFactory.getService(AccountService.class);
            List<Account> accountList= accountService.readBySubscriber(sub_id);
            boolean allIsPaid = true;
            for (Account account : accountList) {
                if (!account.isPaid()) {
                    allIsPaid = false;
                    break;
                }
            
            }
            request.setAttribute("all_paid", allIsPaid);
            request.setAttribute("settings", SettingsManager.getSettings());
            accountService.close();
            return ActionResult.forward("mainUser.jsp");
        }

        return ActionResult.redirect("login.view");

    }
}
