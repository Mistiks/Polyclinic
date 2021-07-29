package view;

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
        User user = this.userService.get(login);

        if (user == null) {
            return null;
        }

        if (!Objects.equals(user.getHash(), hashCreator.createHash(password.concat(user.getSalt())))) {
            return null;
        }

        return user;
    }

    @Override
    public User googleAuthentication(String mail, String id) {
        User user = this.userService.getByGoogleId(id);

        if (user == null) {
            user = new User(mail, id, mail);
            this.userService.signUpGoogle(user);
            return user;
        }

        return user;
    }
}
