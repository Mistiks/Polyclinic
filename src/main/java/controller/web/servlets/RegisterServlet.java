package controller.web.servlets;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import view.api.IUserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/signUp")
public class RegisterServlet {

    /** Constant attribute name for "SIGN_UP_FAIL" flag */
    private static final String SIGN_UP_FAIL = "fail_sign_up";

    /** Instance of class that implements IUserService interface */
    private final IUserService userService;

    /** Default constructor which connect IUserService interface to servlet
     *
     * @param userService instance of IUserService interface
     */
    public RegisterServlet(IUserService userService) {
        this.userService = userService;
    }

    /**
     * GET request processing method. Redirects to signUp page
     *
     * @return URL of registration page
     */
    @GetMapping
    public String doGet() {
        return "signUp";
    }

    /**
     * POST request processing method. Register new user if all field are filled with data (username must be unique).
     * Redirects to sign up page with error message if register process fails.
     *
     * @param request request information for HTTP servlets
     * @return destination URL
     */
    @PostMapping
    public String doPost(HttpServletRequest request, Model model) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = new User(username);
            this.userService.signUp(user, password);
            return "redirect:/home";
        } catch (IllegalArgumentException e){
            model.addAttribute(SIGN_UP_FAIL, e.getMessage());
            return "signUp";
        }
    }
}
