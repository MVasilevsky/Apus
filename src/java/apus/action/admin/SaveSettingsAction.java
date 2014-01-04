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
 * <code>SaveSettingsAction</code> class represents admin action, used to save
 * system settings
 *
 * @author M. Vasilevsky
 */
public class SaveSettingsAction implements Action {

    @Override
    public String getName() {
        return "save.settings";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        ApusSettings apusSettings = new ApusSettings();
        if ((request.getParameter("prefix") != null) && (request.getParameter("length") != null) && (request.getParameter("tariff") != null)) {
            try {
                apusSettings.setNumberPrefix(request.getParameter("prefix"));
                apusSettings.setNumberSize(Integer.parseInt(request.getParameter("length")));
                apusSettings.setTariff(Integer.parseInt(request.getParameter("tariff")));
                apusSettings.setDefaultLanguage(request.getParameter("lang"));
                apusSettings.setQuickSearch(request.getParameter("qsearch") != null ? true : false);
                SettingsManager.setSettings(apusSettings);
                request.getSession().setAttribute("win", res.getString("settings.saved"));
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("fail", res.getString("wrong.value"));
            } catch (DatabaseException e) {
                request.getSession().setAttribute("fail", res.getString("can't.save.settings"));
            }
        } else {
            request.getSession().setAttribute("fail", res.getString("settings.not.saved"));
        }
        return ActionResult.redirect("system.settings");
    }
}
