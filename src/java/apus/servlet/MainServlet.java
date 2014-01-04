package apus.servlet;

import apus.action.Action;
import apus.action.ActionManager;
import apus.action.ActionResult;
import apus.action.account.AccountCreateMonthly;
import apus.action.account.AccountDeleteAction;
import apus.action.account.AccountListAction;
import apus.action.admin.EmployeeListAdministrationAction;
import apus.action.admin.SaveSettingsAction;
import apus.action.admin.SystemSettingsAction;
import apus.action.admin.UserAddAction;
import apus.action.admin.UserDeleteAction;
import apus.action.admin.UserSaveAction;
import apus.action.call.CallListAction;
import apus.action.cashier.CashierAccSearchAction;
import apus.action.cashier.CashierSearchAction;
import apus.action.cashier.PaidAction;
import apus.action.common.DefaultAction;
import apus.action.common.LanguageChangeAction;
import apus.action.common.PasswordSaveAction;
import apus.action.common.SettingsAction;
import apus.action.common.ViewLoginPageAction;
import apus.action.common.ViewMainPageAction;
import apus.action.organization.OrganizationAction;
import apus.action.organization.OrganizationCreateAction;
import apus.action.organization.OrganizationDeleteAction;
import apus.action.organization.OrganizationEditAction;
import apus.action.organization.OrganizationListAction;
import apus.action.organization.OrganizationSaveAction;
import apus.action.organization.OrganizationSearchAction;
import apus.action.person.PersonAction;
import apus.action.person.PersonCreateAction;
import apus.action.person.PersonDeleteAction;
import apus.action.person.PersonEditAction;
import apus.action.person.PersonListAction;
import apus.action.person.PersonSaveAction;
import apus.action.person.PersonSearchAction;
import apus.action.phonenumber.CallsSpecialListAction;
import apus.action.phonenumber.PhoneNumberAction;
import apus.action.phonenumber.PhoneNumberCreateAction;
import apus.action.phonenumber.PhoneNumberDeleteAction;
import apus.action.phonenumber.PhoneNumberEditAction;
import apus.action.phonenumber.PhoneNumberListAction;
import apus.action.phonenumber.PhoneNumberSaveAction;
import apus.action.phonenumber.PhoneNumberSearchAction;
import apus.authorisation.UserAuthorisation;
import apus.common.Configuration;
import apus.entity.User;
import apus.exception.DatabaseException;
import apus.persistence.ConnectionPool;
import apus.persistence.SettingsManager;
import apus.service.ServiceFactory;
import apus.service.UserService;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>MainServlet</code> servlet performs all actions in system, except log
 * in and log out
 *
 * @author M. Vasilevsky
 */
public class MainServlet extends HttpServlet {

    private ActionManager actionManager;
    private static String USER_AUTH = "userAuth";
    private static final Logger logger = Logger.getLogger(MainServlet.class.getName());

    @Override
    public void init() throws ServletException {

        logger.info("Main servlet initializing..");
        
        try {
            String url = getServletConfig().getInitParameter("url");
            String login = getServletConfig().getInitParameter("login");
            String password = getServletConfig().getInitParameter("password");
            String driver = Configuration.JDBC_DRIVER;//getServletConfig().getInitParameter("driver");
            int cpSize = Integer.parseInt(getServletConfig().getInitParameter("cpsize"));
            ConnectionPool.init(url, login, password, driver, cpSize);
        } catch (DatabaseException e) {
            throw new ServletException("Can't initialize connection pool!");
        }

        actionManager = new ActionManager();
        actionManager.addAction(new PersonListAction());
        actionManager.addAction(new PersonAction());
        actionManager.addAction(new PersonCreateAction());
        actionManager.addAction(new PersonDeleteAction());
        actionManager.addAction(new PersonEditAction());
        actionManager.addAction(new PersonSaveAction());
        actionManager.addAction(new PersonSearchAction());
        actionManager.addAction(new OrganizationAction());
        actionManager.addAction(new OrganizationCreateAction());
        actionManager.addAction(new OrganizationDeleteAction());
        actionManager.addAction(new OrganizationEditAction());
        actionManager.addAction(new OrganizationListAction());
        actionManager.addAction(new OrganizationSaveAction());
        actionManager.addAction(new OrganizationSearchAction());
        actionManager.addAction(new CallListAction());
        actionManager.addAction(new AccountDeleteAction());
        actionManager.addAction(new AccountListAction());
        actionManager.addAction(new AccountCreateMonthly());
        actionManager.addAction(new PhoneNumberCreateAction());
        actionManager.addAction(new PhoneNumberDeleteAction());
        actionManager.addAction(new PhoneNumberEditAction());
        actionManager.addAction(new PhoneNumberListAction());
        actionManager.addAction(new PhoneNumberSaveAction());
        actionManager.addAction(new PhoneNumberSearchAction());
        actionManager.addAction(new PhoneNumberAction());
        actionManager.addAction(new ViewMainPageAction());
        actionManager.addAction(new ViewLoginPageAction());

        actionManager.addAction(new CashierSearchAction());
        actionManager.addAction(new CashierAccSearchAction());
        actionManager.addAction(new PaidAction());


        actionManager.addAction(new SystemSettingsAction());
        actionManager.addAction(new SaveSettingsAction());
        actionManager.addAction(new EmployeeListAdministrationAction());
        actionManager.addAction(new UserAddAction());
        actionManager.addAction(new UserSaveAction());
        actionManager.addAction(new UserDeleteAction());

        actionManager.addAction(new SettingsAction());
        actionManager.addAction(new PasswordSaveAction());
        actionManager.addAction(new LanguageChangeAction());

        actionManager.addAction(new CallsSpecialListAction());

        actionManager.addAction(new DefaultAction());
    }

    @Override
    public void destroy() {
        ConnectionPool.close();
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        try {
            if (request.getSession().getAttribute("lang") == null) {
                request.getSession().setAttribute("lang", SettingsManager.getSettings().getDefaultLanguage());
            }
        } catch (DatabaseException e) {
            RequestDispatcher requestDispatcher = getServletContext()
                        .getRequestDispatcher("/WEB-INF/View/error.jsp");
            requestDispatcher.forward(request, response);
            return;
        }

        String actionName = request.getParameter("action");
        boolean loginAction = false;

        if ((actionName == null) || (actionManager.findAction(actionName) == null)) {
            actionName = "default.action";
        }

        if (actionName.equals("login.view")) {
            loginAction = true;
        }

        if ((request.getSession().getAttribute(USER_AUTH) != null) || loginAction) {
            User user = new User();

            if (!loginAction) {
                user.setLogin(((UserAuthorisation) request.getSession().getAttribute(USER_AUTH)).getLogin());
            }

            if (ServiceFactory.getService(UserService.class).isActionAllowed(user, actionName) || loginAction) {

                Action action = actionManager.findAction(actionName);

                ActionResult forward;
                try {
                    forward = action.execute(request, response);

                    if (forward.isForward()) {
                        RequestDispatcher requestDispatcher = getServletContext()
                                .getRequestDispatcher("/WEB-INF/View/" + forward.getViewJsp());
                        requestDispatcher.forward(request, response);
                    }

                    if (forward.isRedirect()) {
                        response.sendRedirect(request.getContextPath() + "/main?action=" + forward.getAction());
                    }
                } catch (DatabaseException e) {
                    if (((UserAuthorisation) request.getSession().getAttribute(USER_AUTH)).getRole().equals(UserAuthorisation.Role.ADMIN) && (System.getenv("APUS_DB_PATH") == null)) {
                        RequestDispatcher requestDispatcher = getServletContext()
                                .getRequestDispatcher("/WEB-INF/View/databaseSettingError.jsp");
                        requestDispatcher.forward(request, response);
                    } else {
                        RequestDispatcher requestDispatcher = getServletContext()
                                .getRequestDispatcher("/WEB-INF/View/error.jsp");
                        requestDispatcher.forward(request, response);
                    }
                } catch (Exception e) {
                    RequestDispatcher requestDispatcher = getServletContext()
                            .getRequestDispatcher("/WEB-INF/View/error.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else {
                RequestDispatcher requestDispatcher = getServletContext()
                        .getRequestDispatcher("/WEB-INF/View/accessDeniedPage.jsp");
                requestDispatcher.forward(request, response);
            }

        } else {
            response.sendRedirect(request.getContextPath() + "/main?action=login.view");
        }
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
