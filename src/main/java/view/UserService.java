package view;

import model.Password;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import storage.api.IPasswordRepository;
import storage.api.IUserRepository;
import utils.api.IHashCreator;
import view.api.IUserService;
import java.util.Random;

public class UserService implements IUserService {

    private final IUserRepository repository;
    private final IPasswordRepository passwordRepository;
    private final IHashCreator hashCreator;

    public UserService(IUserRepository repository, IPasswordRepository passwordRepository, IHashCreator hashCreator) {
        this.repository = repository;
        this.passwordRepository = passwordRepository;
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
        Password passwordData = createPasswordObject(user.getLogin(), password);
        user.setStatus("unverified");
        user.setRole("patient");
        this.repository.save(user);
        this.passwordRepository.save(passwordData);
    }

    @Override
    public Password getPasswordObject(String login) {
        return this.passwordRepository.getByLogin(login);
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

    private Password createPasswordObject(String login, String password) {
        String salt = createSalt();
        return new Password(login, hashCreator.createHash(password.concat(salt)), salt);
    }
}
