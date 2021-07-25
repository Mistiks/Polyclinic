package view;

import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import storage.api.IUserRepository;
import utils.api.IHashCreator;
import view.api.IUserService;
import java.util.Random;

public class UserService implements IUserService {

    private final IUserRepository repository;
    private final IHashCreator hashCreator;

    public UserService(IUserRepository repository, IHashCreator hashCreator) {
        this.repository = repository;
        this.hashCreator = hashCreator;
    }

    /**
     * Retrieves user with provided login
     *
     * @param login username for search
     * @return User object with provided login if user exist, null if doesn't
     */
    @Override
    public User get(String login) {
        return this.repository.getByLogin(login);
    }

    /**
     * Register user if all fields are filled, and username is unique
     *
     * @param user User object with user registration data
     */
    @Override
    public void signUp(User user, String password) {
        this.validationForSignUp(user, password);
        user.setSalt(createSalt());
        user.setHash(createPasswordHashWithSalt(password, user.getSalt()));
        this.repository.save(user);
    }

    @Override
    public void signUpGoogle(User user) {
        this.repository.save(user);
    }

    /**
     * Checks if all fields are filled with data and username is unique
     * Throws IllegalArgument Exception if not
     *
     * @param user User object with user registration data
     * @param password
     */
    private void validationForSignUp(User user, String password) {

        if (this.nullOrEmpty(user.getLogin()) || this.nullOrEmpty(password)) {
            throw new IllegalArgumentException("Some fields are empty!");
        }

        if (this.get(user.getLogin()) != null) {
            throw new IllegalArgumentException("User already exists!");
        }
    }

    /**
     * Checks if string equals null or empty
     *
     * @param val string to check
     * @return true if string isn't empty, false if empty or null
     */
    private boolean nullOrEmpty(String val) {
        return val == null || val.isEmpty();
    }

    private String createSalt() {
        Random random = new Random();
        int length = 5 + random.nextInt(5); // length from 5 to 9

        return RandomStringUtils.random(length, true, false);
    }

    private String createPasswordHashWithSalt(String password, String salt) {
        return hashCreator.createHash(password.concat(salt));
    }
}
