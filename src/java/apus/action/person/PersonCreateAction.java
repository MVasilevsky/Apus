package apus.action.person;

import apus.action.Action;
import apus.action.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>PersonCreateAction</code> class represents action, used to add new
 * person to DB
 *
 * @author M. Vasilevsky
 */
public class PersonCreateAction implements Action {

    @Override
    public String getName() {
        return "person.create";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        return ActionResult.forward("PersonView/CreatePersonView.jsp");
    }
}
