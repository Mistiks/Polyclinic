package controller.web.servlets;

import model.User;
import model.dto.LoginData;
import model.dto.UserProfileDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import view.api.ICountryService;
import view.api.IUserService;

@Controller
@RequestMapping(value = "/dashboard")
public class AdminServlet {
    private final IUserService userService;
    private final ICountryService countryService;

    public AdminServlet(IUserService userService, ICountryService countryService) {
        this.userService = userService;
        this.countryService = countryService;
    }

    @GetMapping
    public String doGet() {
        return "dashboard";
    }

    @GetMapping(value = "/profiles")
    public String getProfiles() {
        return "profilesMenu";
    }

    @GetMapping(value = "/profiles/showPage")
    public String getProfilesShow() {
        return "profilesShow";
    }

    @GetMapping(value = {"/profiles/show/{username}", "/profiles/update/{username}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserProfileDTO> getProfileInfo(@PathVariable(required = false) String username) {
        try {
            UserProfileDTO userProfileDTO = userService.getAllUserInfo(username);
            return new ResponseEntity<>(userProfileDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/profiles/updatePage")
    public String loadUpdatePage(Model model) {
        model.addAttribute("countries", countryService.getAll());
        return "profilesUpdate";
    }

    @PutMapping(value = "/profiles/update/{username}")
    @ResponseBody
    public ResponseEntity<UserProfileDTO> updateProfile(@RequestBody UserProfileDTO userProfileDTO) {
        try {
            UserProfileDTO updated = userService.update(userProfileDTO);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/profiles/createPage")
    public String loadCreatePage() {
        return "profilesCreate";
    }

    @PostMapping(value = "/profiles/create")
    public ResponseEntity<?> createProfile(@RequestBody LoginData loginData) {
        try {
            User user = new User(loginData.getUsername());
            userService.signUp(user, loginData.getPassword());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/profiles/deletePage")
    public String loadDeletePage() {
        return "profilesDelete";
    }

    @DeleteMapping(value = "/profiles/delete")
    public ResponseEntity<?> deleteProfile(@RequestBody LoginData loginData) {
        try {
            userService.delete(loginData.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
