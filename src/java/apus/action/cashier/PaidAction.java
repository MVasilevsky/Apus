package apus.action.cashier;

import apus.action.Action;
import apus.action.ActionResult;
import apus.authorisation.UserAuthorisation;
import apus.service.AccountService;
import apus.service.ServiceFactory;
import apus.service.impl.AccountServiceImpl;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>PaidAction</code> class represents cashier action, used to pay the bill
 *
 * @author M. Vasilevsky
 */
public class PaidAction implements Action {

    private final static Logger logger = Logger.getLogger(PaidAction.class.getName());

    @Override
    public String getName() {
        return "paid";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        AccountService accountService = ServiceFactory.getService(AccountService.class);
        try {
            accountService.payAccount(Integer.parseInt(request.getParameter("accountId")));
            logger.log(Level.INFO, "Account with id={0} was carried out by {1}", new Object[]{request.getParameter("accountId"), ((UserAuthorisation) request.getSession().getAttribute("userAuth")).getLogin()});
            request.getSession().setAttribute("win", res.getString("payment.win"));
        } catch (Exception e) {
            request.getSession().setAttribute("fail", res.getString("payment.fail"));
        }
        accountService.close();
        return ActionResult.redirect("main.view");
    }
}
