package controller.web.servlets;

import model.User;
import javax.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import view.api.IAuthService;

/**
 * Class which implements login functionality
 *
 * @author Vadim Rataiko
 * @version 1.1.1
 */
@Controller
@RequestMapping(value = "/signIn")
public class LoginServlet {

    /** Constant attribute name for current user storage */
    private static final String CURRENT_USER = "currentUser";

    /** Constant attribute name for "SIGN_IN_FAIL" flag */
    private static final String SIGN_IN_FAIL = "fail_sign_in";

    /** Instance of class that implements IAuthService interface */
    private final IAuthService authService;

    /**
     * Default constructor which connect IAuthService interface to servlet
     *
     * @param authService instance of IAuthService interface
     */
    public LoginServlet(IAuthService authService) {
        this.authService = authService;
    }

    /**
     * GET request processing method. Redirects to signIn page
     *
     * @return URL of login page
     */
    @GetMapping
    public String doGet() {
        return "signIn";
    }

    /**
     * POST request processing method. Redirects to profile page if user with entered username and password exists.
     * Redirects to sign in page with error message if login process fails.
     *
     * @param request current HTTP request
     * @return destination URL
     */
    @PostMapping
    public String doPost(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = authService.authentication(username, password);

        if (user == null) {
            model.addAttribute(SIGN_IN_FAIL, "Incorrect login or password");
            return "signIn";
        } else {
            session.setAttribute(CURRENT_USER, user);
            return "redirect:/home";
        }
    }
}
