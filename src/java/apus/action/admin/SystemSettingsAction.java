package apus.action.admin;

import apus.action.Action;
import apus.action.ActionResult;
import apus.common.ApusSettings;
import apus.exception.DatabaseException;
import apus.persistence.SettingsManager;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>SystemSettingsAction</code> class represents admin action, used to view
 * and edit system settings
 *
 * @author M. Vasilevsky
 */
public class SystemSettingsAction implements Action {

    @Override
    public String getName() {
        return "system.settings";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        try {
            ApusSettings apusSettings = SettingsManager.getSettings();
            request.setAttribute("apusSettings", apusSettings);
            return ActionResult.forward("systemSettings.jsp");
        } catch (DatabaseException e) {
            request.getSession().setAttribute("fail", res.getString("сan't.read.settings"));
            return ActionResult.redirect("main.view");
        }

    }
}
