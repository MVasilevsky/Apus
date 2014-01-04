package apus.action.account;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.Account;
import apus.persistence.DataBaseStatistics;
import apus.service.AccountService;
import apus.service.ServiceFactory;
import apus.service.impl.AccountServiceImpl;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>AccountCreateMonthly</code> class represents action, which creates
 * monthly accounts
 *
 * @author M. Vasilevsky
 */
public class AccountCreateMonthly implements Action {

    @Override
    public String getName() {
        return "account.monthly";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        AccountService accountService = ServiceFactory.getService(AccountServiceImpl.class);
            int start = DataBaseStatistics.getStatistics().get("Accounts");
            accountService.addMonthlyAccounts();
            int count = DataBaseStatistics.getStatistics().get("Accounts") - start;
            if (count > 0) {
                request.getSession().setAttribute("win", count + " " + res.getString("accounts.created"));
            } else {
               request.getSession().setAttribute("win", res.getString("all.accounts.created"));
            }
            List<Account> accountList = accountService.findAll();
            request.setAttribute("accountList", accountList);
            accountService.close();
            return ActionResult.redirect("account.list");
        }
}
