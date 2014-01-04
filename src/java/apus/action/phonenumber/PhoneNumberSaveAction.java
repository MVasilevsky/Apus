package apus.action.phonenumber;

import apus.action.Action;
import apus.action.ActionResult;
import apus.entity.*;
import apus.entity.impl.PhoneNumberImpl;
import apus.exception.DatabaseException;
import apus.exception.EntityNotFoundException;
import apus.service.OrganizationService;
import apus.service.PersonService;
import apus.service.PhoneNumberService;
import apus.service.ServiceFactory;
import apus.service.impl.OrganizationServiceImpl;
import apus.service.impl.PersonServiceImpl;
import apus.service.impl.PhoneNumberServiceImpl;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>PhoneNumberSaveAction</code> class represents action, used to save
 * phone number information
 *
 * @author M. Vasilevsky
 */
public class PhoneNumberSaveAction implements Action {

    @Override
    public String getName() {
        return "phoneNumber.save";
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle res = ResourceBundle.getBundle("Messages", new Locale((String) request.getSession().getAttribute("lang")));
        
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }

        if ((request.getParameter("id") == null) || (request.getParameter("id").isEmpty())
                || (request.getParameter("sub_id") == null) || (request.getParameter("sub_id").isEmpty())
                || (request.getParameter("number") == null) || (request.getParameter("number").isEmpty())) {
            request.getSession().setAttribute("fail", res.getString("Wrong data"));
            return ActionResult.redirect("phoneNumber.list");
        }

        // if Save button has been pressed
        {
            if (request.getParameter("saveButton") != null) {
                PhoneNumberService phoneNumberService = ServiceFactory.getService(PhoneNumberService.class);
                try {
                    int id = Integer.parseInt(request.getParameter("id"));

                    PhoneNumber phoneNumber = (id == 0) ? new PhoneNumberImpl() : phoneNumberService.read(id);
                    String number = request.getParameter("number");
                    phoneNumber.setNumber(number);

                    int sub_id = Integer.parseInt(request.getParameter("sub_id"));
                    
                    Subscriber sub;
                    try {
                        PersonService personService = ServiceFactory.getService(PersonService.class);
                    sub = personService.read(sub_id);
                    personService.close();
                    } catch (EntityNotFoundException e) {
                        OrganizationService organizationService = ServiceFactory.getService(OrganizationService.class);
                    sub = organizationService.read(sub_id);
                    organizationService.close();
                    }
                        
                    phoneNumber.setOwner(sub);
                    String sub_type = sub.getSubscriberType();

                    request.getSession().setAttribute("id", sub_id);

                    if (id != 0) {
                        phoneNumberService.update(phoneNumber); // update phoneNumber
                        request.getSession().setAttribute("win", res.getString("number.updated"));
                    } else {
                        phoneNumberService.create(phoneNumber); // create phoneNumber
                        request.getSession().setAttribute("win", res.getString("number.added"));

                        if (sub_type.equals("person")) {
                            return ActionResult.redirect("person.view");
                        } else {
                            return ActionResult.redirect("organization.view");
                        }
                    }

                } catch (DatabaseException e) {
                    request.getSession().setAttribute("fail", res.getString("number.already.exists"));
                } catch (Exception e) {
                    request.getSession().setAttribute("fail", res.getString("number.not.saved"));
                } finally {
                    phoneNumberService.close();
                }
            } else {
                request.getSession().setAttribute("fail", res.getString("number.not.saved"));
            }
        }

        return ActionResult.redirect("phoneNumber.list");
    }
}
