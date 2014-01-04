package apus.action.account;

import apus.action.Action;
import apus.action.ActionResult;
import apus.authorisation.UserAuthorisation;
import apus.entity.Account;
import apus.service.AccountService;
import apus.service.ServiceFactory;
import apus.service.impl.AccountServiceImpl;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The <code>AccountListAction</code> class represents action, which views accounts list 
 * @author M. Vasilevsky
 */
public class AccountListAction implements Action {

    @Override
    public String getName() {
        return "account.list";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        List<Account> accountList;
        AccountService accountService = ServiceFactory.getService(AccountService.class);

        UserAuthorisation requesterAuthorisation = ((UserAuthorisation) request.getSession().getAttribute("userAuth"));
        UserAuthorisation.Role requesterRole = requesterAuthorisation.getRole();

        // request concrete user
        if (request.getParameter("sub_id") != null) {
            try {
                int sub_id = Integer.parseInt(request.getParameter("sub_id"));
                // user requests somebody other
                if ((requesterRole == UserAuthorisation.Role.USER) && (requesterAuthorisation.getSubscriberId() != sub_id)) {
                    return ActionResult.redirect("main.view");
                    // user requests himself or admin requests somebody
                } else {
                    accountList = accountService.readBySubscriber(sub_id);
                    request.setAttribute("accountList", accountList);
                    return ActionResult.forward("AccountView/AccountListView.jsp");
                }
            } catch (NumberFormatException e) {
                if (requesterRole == UserAuthorisation.Role.USER) {
                    return ActionResult.redirect("main.view");
                } else {
                    request.setAttribute("fail", res.getString("incorrect.subscriber.id"));
                    return ActionResult.forward("AccountView/AccountListView.jsp");
                }
            } finally {
                accountService.close();
            }
            // request all users
        } else {
            // not admin
            if (requesterRole != UserAuthorisation.Role.ADMIN) {
                accountService.close();
                return ActionResult.redirect("main.view");
                // admin
            } else {
                accountList = accountService.findAll();
                request.setAttribute("accountList", accountList);
                accountService.close();
                return ActionResult.forward("AccountView/AccountListView.jsp");
            }
        }
    }
}