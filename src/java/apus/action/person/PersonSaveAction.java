package apus.action.person;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.Person;
import apus.entity.impl.PersonImpl;
import apus.exception.DatabaseException;
import apus.service.PersonService;
import apus.service.ServiceFactory;
import apus.service.impl.PersonServiceImpl;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>PersonSaveAction</code> class represents action, used to save person
 * information
 *
 * @author M. Vasilevsky
 */
public class PersonSaveAction implements Action {

    @Override
    public String getName() {
        return "person.save";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }

        if ((request.getParameter("id") == null) || (request.getParameter("id").isEmpty())
                || (request.getParameter("name") == null) || (request.getParameter("name").isEmpty())
                || (request.getParameter("passport") == null) || (request.getParameter("passport").isEmpty())
                || (request.getParameter("address") == null) || (request.getParameter("address").isEmpty())) {
            request.getSession().setAttribute("fail", res.getString("wrong.data"));
            return ActionResult.redirect("person.list");
        }

        // if Save button have been pressed
        if (request.getParameter("saveButton") != null) {
            PersonService personService = ServiceFactory.getService(PersonService.class);
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Person person = (id == 0) ? new PersonImpl() : personService.read(id);

                // load data from request
                person.setName(request.getParameter("name"));
                person.setPassportNumber(request.getParameter("passport").toUpperCase());
                person.setAddress(request.getParameter("address"));

                if (id != 0) {
                    personService.update(person); // update
                    request.getSession().setAttribute("win", person.getName() + " " + res.getString("updated"));
                } else {
                    personService.create(person); // create
                    request.getSession().setAttribute("win", person.getName() + " " + res.getString("added"));
                }
            } catch (DatabaseException | NumberFormatException e) {
                request.getSession().setAttribute("fail", res.getString("person.not.saved"));
            } finally {
                personService.close();
            }
        } else {
            request.getSession().setAttribute("fail", res.getString("person.not.saved"));
        }
        
        return ActionResult.redirect("person.list");
    }
}
