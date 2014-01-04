package apus.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The <code>Action</code> interface represents action, used in Front Controller design pattern 
 * @author M. Vasilevsky
 */
public interface Action {
    String getName();
    ActionResult execute(HttpServletRequest request, HttpServletResponse response);
}
