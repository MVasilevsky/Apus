package apus.action.person;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.impl.PersonImpl;
import apus.exception.EntityNotFoundException;
import apus.service.PersonService;
import apus.service.ServiceFactory;
import apus.service.impl.PersonServiceImpl;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>PersonDeleteAction</code> class represents action, used to remove
 * person
 *
 * @author M. Vasilevsky
 */
public class PersonDeleteAction implements Action {

    @Override
    public String getName() {
        return "person.delete";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        if ((request.getParameter("id") != null) && (request.getParameter("name") != null)) {
            PersonService personService = ServiceFactory.getService(PersonService.class);
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                personService.remove(new PersonImpl(id));
                request.getSession().setAttribute("win", request.getParameter("name") + " " + res.getString("removed"));
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("fail", res.getString("incorrect.id"));
            } catch (EntityNotFoundException e) {
                request.getSession().setAttribute("fail", res.getString("person.not.found"));
            } finally {
                personService.close();
            }
        } else {
            request.getSession().setAttribute("fail", res.getString("person.not.removed"));
        }

        if (request.getParameter("search") != null) {
            request.getSession().setAttribute("search", request.getParameter("search"));
        }

        return ActionResult.redirect("person.list");
    }
}
