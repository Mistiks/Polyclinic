package view.api;

import model.Password;
import model.User;

public interface IUserService {

    /**
     * Retrieves user with provided login
     *
     * @param login username for search
     * @return User object with provided login if user exist, null if doesn't
     */
    User get(String login);

    /**
     * Register user if all fields are filled, and username is unique
     *
     * @param user User object with user registration data
     */
    void signUp(User user, String password);

    Password getPasswordObject(String login);
}
