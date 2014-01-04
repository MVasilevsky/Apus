package apus.action.common;

import apus.action.Action;
import apus.action.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>DefaultAction</code> class represents action, used when requested
 * action not found
 *
 * @author M. Vasilevsky
 */
public class DefaultAction implements Action {

    @Override
    public String getName() {
        return "default.action";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        return ActionResult.redirect("main.view");
    }
}
