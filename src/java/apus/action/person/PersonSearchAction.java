package apus.action.person;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.Person;
import apus.exception.EntityNotFoundException;
import apus.service.PersonService;
import apus.service.ServiceFactory;
import apus.service.impl.PersonServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>PersonSearchAction</code> class represents action, used to search
 * persons
 *
 * @author M. Vasilevsky
 */
public class PersonSearchAction implements Action {

    @Override
    public String getName() {
        return "person.search";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<Person> personList;
        PersonService personService = ServiceFactory.getService(PersonService.class);
        try {
            personList = personService.readByName(request.getParameter("name"));
        } catch (EntityNotFoundException e) {
            personList = new ArrayList<>();
        }
        personService.close();
        request.setAttribute("personList", personList);
        return ActionResult.forward("search/personSearch.jsp");
    }
}
