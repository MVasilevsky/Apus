package apus.action.person;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.Person;
import apus.persistence.SettingsManager;
import apus.service.PersonService;
import apus.service.ServiceFactory;
import apus.service.impl.PersonServiceImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>PersonListAction</code> class represents action, used to view person
 * list
 *
 * @author M. Vasilevsky
 */
public class PersonListAction implements Action {

    @Override
    public String getName() {
        return "person.list";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<Person> personList;
        PersonService personService = ServiceFactory.getService(PersonService.class);
        if (request.getSession().getAttribute("search") == null) {
            personList = personService.findAll();
        } else {
            personList = personService.readByName((String) request.getSession().getAttribute("search"));
        }
        request.setAttribute("personList", personList);
        request.setAttribute("sets", SettingsManager.getSettings());
        personService.close();
        return ActionResult.forward("PersonView/PersonListView.jsp");
    }
}
