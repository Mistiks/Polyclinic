package view.api;

import model.User;

/**
 * Interface for user's authentication
 *
 * @author Ilya Shadryn
 * @version 1.0
 */
public interface IAuthService {

    /**
     * Checks if user with provided login and password exists
     *
     * @param login user's login
     * @param password user's password
     * @return User object that has same login and password, null if user doesn't exist
     */
    User authentication(String login, String password);

    User googleAuthentication(String mail, String id);
}
