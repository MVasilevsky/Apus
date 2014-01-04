package apus.action.person;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.Person;
import apus.exception.EntityNotFoundException;
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
 * <code>PersonEditAction</code> class represents action, used to edit person
 * information
 *
 * @author M. Vasilevsky
 */
public class PersonEditAction implements Action {

    @Override
    public String getName() {
        return "person.edit";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        PersonService personService = ServiceFactory.getService(PersonService.class);
        if (request.getParameter("id") != null) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Person person = personService.read(id);
                request.setAttribute("person", person);
                return ActionResult.forward("PersonView/EditPersonView.jsp");
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
