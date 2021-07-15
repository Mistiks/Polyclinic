package view;

import model.Password;
import model.User;
import utils.api.IHashCreator;
import view.api.IAuthService;
import view.api.IUserService;

import java.util.Objects;

/**
 * Class-implementation if IAuthService interface. Checks if user has
 * right to use application
 *
 * @author Vadim Rataiko
 * @version 1.1
 */
public class AuthService implements IAuthService {

    /** Instance of IUserService interface */
    private final IUserService userService;
    private final IHashCreator hashCreator;

    /** Private constructor that defines implementation of IUserService interface */
    public AuthService(IUserService userService, IHashCreator hashCreator) {
        this.userService = userService;
        this.hashCreator = hashCreator;
    }

    /**
     * Checks if user with provided login and password exists
     *
     * @param login user's login
     * @param password user's password
     * @return User object that has same login and password, null if user doesn't exist
     */
    @Override
    public User authentication(String login, String password) {
        Password passwordData = this.userService.getPasswordObject(login);
        User user = this.userService.get(login);

        if (user == null || passwordData == null) {
            return null;
        }

        if (!Objects.equals(passwordData.getHash(), hashCreator.createHash(password.concat(passwordData.getSalt())))) {
            return null;
        }

        return user;
    }
}
