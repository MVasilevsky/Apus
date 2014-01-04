package apus.action.common;

import apus.action.Action;
import apus.action.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>SettingsAction</code> class represents action, used to view settings
 * page
 *
 * @author M. Vasilevsky
 */
public class SettingsAction implements Action {

    @Override
    public String getName() {
        return "settings";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        return ActionResult.forward("settings.jsp");
    }
}
