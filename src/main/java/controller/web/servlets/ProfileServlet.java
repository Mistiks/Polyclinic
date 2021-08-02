package controller.web.servlets;

import model.dto.UserDTO;
import model.dto.UserProfileDTO;
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

    public ProfileServlet(IUserService userService, ICountryService countryService) {
        this.userService = userService;
        this.countryService = countryService;
    }

    @GetMapping
    public String doGet(Model model) {
        model.addAttribute("countries", countryService.getAll());
        return "profile";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> updateProfile(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        try {
            userService.update(userDTO, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}