package apus.servlet;

import apus.authorisation.UserAuthorisation;
import apus.authorisation.UserAuthorisationFactory;
import apus.authorisation.UserValidator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The
 * <code>SessionServlet</code> servlet performs log in and log out actions in
 * system
 *
 * @author M. Vasilevsky
 */
public class SessionServlet extends HttpServlet {

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
    public static final String USER_AUTH = "userAuth";
    private static final Logger logger = Logger.getLogger(SessionServlet.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //UserAuthorisation userAuth = (UserAuthorisation) request.getSession().getAttribute(USER_AUTH);

            if ("logout".equals(request.getParameter("action"))) {
                UserAuthorisation userAuthorisation = (UserAuthorisation) request.getSession().getAttribute(USER_AUTH);
                if (userAuthorisation != null) {
                    logger.log(Level.INFO, "{0} was logged out", userAuthorisation.getLogin());
                }
                request.getSession().invalidate();
            }

            try {
                if ("login".equals(request.getParameter("action"))) {
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    int role = UserValidator.isLoginValid(login, password);
                    if (role != -1) {
                        logger.log(Level.INFO, "{0} was logged in", login);
                        UserAuthorisation userAuth = UserAuthorisationFactory.getAuthorizedUser(login, role);
                        request.getSession().setAttribute(USER_AUTH, userAuth);
                        response.sendRedirect(request.getContextPath() + "/main?action=main.view");
                        if (request.getSession().getAttribute("errorMessage") != null) {
                            request.getSession().removeAttribute("errorMessage");
                        }
                    } else {
                        request.getSession().setAttribute("errorMessage", "Wrong authorisation data");
                        response.sendRedirect(request.getContextPath() + "/main?action=login.view");
                    }
                } else {
                    response.sendRedirect(request.getContextPath() + "/main?action=login.view");
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Exception while logging in: {0}", e.getMessage());
                response.sendRedirect(request.getContextPath() + "/main?action=error.view");
            }
        } finally {
            out.close();
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
