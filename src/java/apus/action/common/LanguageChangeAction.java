package apus.action.common;

import apus.action.Action;
import apus.action.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>LanguageChangeAction</code> class represents action, used to change
 * user interface language
 *
 * @author M. Vasilevsky
 */
public class LanguageChangeAction implements Action {

    @Override
    public String getName() {
        return "lang.change";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("lang", request.getParameter("lang"));
        return ActionResult.redirect("settings");
    }
}
