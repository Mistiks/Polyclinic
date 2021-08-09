package controller.web.servlets;

import model.User;
import model.dto.UserDTO;
import model.dto.UserSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import view.api.ICountryService;
import view.api.IUserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/profile")
public class ProfileServlet {

    private final IUserService userService;
    private final ICountryService countryService;
    private final String CURRENT_USER = "currentUser";

    public ProfileServlet(IUserService userService, ICountryService countryService) {
        this.userService = userService;
        this.countryService = countryService;
    }

    @GetMapping(value = "/update")
    public String doGet(Model model, HttpServletRequest request) {
        UserSession user = (UserSession) request.getSession().getAttribute(CURRENT_USER);
        model.addAttribute("profile", userService.getAllUserInfo(user.getLogin()));
        model.addAttribute("countries", countryService.getAll());
        return "profile/profileUpdate";
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<?> updateProfile(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        try {
            userService.update(userDTO, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/show")
    public String showProfile(Model model, HttpServletRequest request) {
        UserSession user = (UserSession) request.getSession().getAttribute(CURRENT_USER);
        model.addAttribute("profile", userService.getAllUserInfo(user.getLogin()));
        return "profile/profileShow";
    }

    @GetMapping(value = "/medcard")
    public String showMedcardRecords(Model model, HttpServletRequest request) {
        UserSession user = (UserSession) request.getSession().getAttribute(CURRENT_USER);
        model.addAttribute("medcard", userService.getAllMedcardInfo(user.getLogin(), request));
        return "profile/profileMedcard";
    }

    @GetMapping(value = "/ticket")
    public String showTalonRecords(Model model, HttpServletRequest request) {
        UserSession user = (UserSession) request.getSession().getAttribute(CURRENT_USER);
        model.addAttribute("talon", userService.getAllTalonInfo(user.getLogin(), request));
        return "profile/profileTalon";
    }
}