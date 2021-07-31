package controller.web.servlets;

import model.User;
import model.dto.LoginData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import view.api.IUserService;

@Controller
@RequestMapping(value = "/signUp")
public class RegisterServlet {

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
     * @return destination URL
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> doPost(@RequestBody LoginData loginData) {
        try {
            User user = new User(loginData.getUsername());
            this.userService.signUp(user, loginData.getPassword());
            return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
