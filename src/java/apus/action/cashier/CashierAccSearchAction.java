package apus.action.cashier;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.Account;
import apus.exception.DatabaseException;
import apus.service.AccountService;
import apus.service.ServiceFactory;
import apus.service.impl.AccountServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>CashierAccSearchAction</code> class represents cashier action, used to
 * search accounts of choosed subscriber
 *
 * @author M. Vasilevsky
 */
public class CashierAccSearchAction implements Action {

    @Override
    public String getName() {
        return "cashier.account.search";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        List<Account> accountList = new ArrayList<>();
        AccountService accountService = ServiceFactory.getService(AccountService.class);
        if (request.getParameter("sub_id") != null) {
            try {
                accountList = accountService.readBySubscriber(Integer.parseInt(request.getParameter("sub_id")));
            } catch (NumberFormatException e) {
                request.setAttribute("fail", res.getString("incorrect.id"));
            } catch (DatabaseException e) {
                request.setAttribute("fail", e.getMessage());
            }
        }
        request.setAttribute("accountList", accountList);
        accountService.close();
        return ActionResult.forward("search/cashierAccSearch.jsp");
    }
}
