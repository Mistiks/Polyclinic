package view.api;

import model.User;
import model.dto.Appointments;
import model.dto.MedCardWithUsername;
import model.dto.UserDTO;
import model.dto.UserProfileDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {

    /**
     * Retrieves user with provided login
     *
     * @param login username for search
     * @return User object with provided login if user exist, null if doesn't
     */
    User get(String login);

    User getById(Integer id);

    User getByGoogleId(String id);

    /**
     * Register user if all fields are filled, and username is unique
     *
     * @param user User object with user registration data
     */
    void signUp(User user, String password);

    void signUpGoogle(User user);

    UserProfileDTO getAllUserInfo(String username);
    UserProfileDTO update(UserProfileDTO input);
    void delete(String username);
    void update (UserDTO input, HttpServletRequest request);

    List<MedCardWithUsername> getAllMedcardInfo(String login, HttpServletRequest request);

    List<Appointments> getAllTalonInfo(String login, HttpServletRequest request);
}
