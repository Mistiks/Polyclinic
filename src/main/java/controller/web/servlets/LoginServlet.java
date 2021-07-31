package controller.web.servlets;

import model.User;
import javax.servlet.http.*;

import model.dto.LoginData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    private final String CURRENT_USER = "currentUser";

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
    @ResponseBody
    public ResponseEntity<?> doPost(HttpServletRequest request, @RequestBody LoginData loginData) {
        HttpSession session = request.getSession();

        User user = authService.authentication(loginData.getUsername(), loginData.getPassword());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            session.setAttribute(CURRENT_USER, user);
            return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
        }
    }
}
