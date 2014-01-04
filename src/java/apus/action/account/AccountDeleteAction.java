package apus.action.account;

import apus.action.Action;
import apus.action.ActionResult;
import apus.exception.EntityNotFoundException;
import apus.service.AccountService;
import apus.service.ServiceFactory;
import apus.service.impl.AccountServiceImpl;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The <code>AccountDeleteAction</code> class represents action, which used to delete account
 * @author M. Vasilevsky
 */
public class AccountDeleteAction implements Action {

    @Override
    public String getName() {
        return "account.delete";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        AccountService accountService = ServiceFactory.getService(AccountService.class);
        if (request.getParameter("id") != null) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                accountService.remove(id);
                request.getSession().setAttribute("win", res.getString("account.removed"));
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("fail", res.getString("incorrect.id"));
            } catch (EntityNotFoundException e) {
                request.getSession().setAttribute("fail", res.getString("account.not.found"));
            }
        } else {
            request.getSession().setAttribute("fail", res.getString("account.not.removed"));
        }
        accountService.close();
        return ActionResult.redirect("account.list");
    }
}