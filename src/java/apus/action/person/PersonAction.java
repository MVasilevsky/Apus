package apus.action.person;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.Person;
import apus.exception.EntityNotFoundException;
import apus.persistence.SettingsManager;
import apus.service.PersonService;
import apus.service.ServiceFactory;
import apus.service.impl.PersonServiceImpl;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>PersonAction</code> class represents action, used to view person
 * information
 *
 * @author M. Vasilevsky
 */
public class PersonAction implements Action {

    @Override
    public String getName() {
        return "person.view";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        PersonService personService = ServiceFactory.getService(PersonService.class);
        if ((request.getParameter("id") != null) || (request.getSession().getAttribute("id") != null)) {
            try {
                int id = (request.getParameter("id") != null) ? (Integer.parseInt(request.getParameter("id"))) : ((Integer) request.getSession().getAttribute("id"));
                Person person = personService.read(id);
                request.setAttribute("person", person);
                request.setAttribute("settings", SettingsManager.getSettings());
                return ActionResult.forward("PersonView/PersonView.jsp");
            } catch (NumberFormatException e) {
                request.setAttribute("fail", res.getString("incorrect.id"));
            } catch (EntityNotFoundException e) {
                request.setAttribute("fail", res.getString("person.not.found"));
            }
        }

        List<Person> personList = personService.findAll();
        request.setAttribute("personList", personList);
        personService.close();
        return ActionResult.forward("PersonView/PersonListView.jsp");
    }
}
