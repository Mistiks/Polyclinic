package view;

import model.Address;
import model.Passport;
import model.User;
import model.dto.UserProfileDTO;
import model.enums.Role;
import model.enums.Status;
import org.apache.commons.lang3.RandomStringUtils;
import storage.api.IAddressRepository;
import storage.api.IPassportRepository;
import storage.api.IUserRepository;
import utils.api.IHashCreator;
import view.api.IUserService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;

public class UserService implements IUserService {

    private final IUserRepository repository;
    private final IPassportRepository passportRepository;
    private final IAddressRepository addressRepository;
    private final IHashCreator hashCreator;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public UserService(IUserRepository repository, IPassportRepository passportRepository,
                       IAddressRepository addressRepository, IHashCreator hashCreator) {
        this.repository = repository;
        this.passportRepository = passportRepository;
        this.addressRepository = addressRepository;
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

    @Override
    public User getByGoogleId(String id) {
        return this.repository.getByGoogleId(id);
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
        user.setRole(Role.USER);
        user.setStatus(Status.UNVERIFIED);
        this.repository.save(user);
    }

    @Override
    public void signUpGoogle(User user) {
        user.setRole(Role.USER);
        user.setStatus(Status.UNVERIFIED);
        this.repository.save(user);
    }

    @Override
    @Transactional
    public UserProfileDTO getAllUserInfo(String username) {
        User user = this.get(username);

        if (user == null) {
            throw new IllegalArgumentException("No user!");
        }


        Passport passport = passportRepository.getByUserId(user.getId());
        Address address = addressRepository.getByUserId(user.getId());
        return createUserProfileDTO(user, passport, address);
    }

    @Override
    @Transactional
    public UserProfileDTO update(UserProfileDTO input) {
        User user = this.get(input.getUsername());
        Passport passport = passportRepository.getByUserId(user.getId());
        Address address = addressRepository.getByUserId(user.getId());

        if (passport == null) {
            passport = new Passport();
        }

        if (address == null) {
            address = new Address();
        }

        user.setFirstName(input.getFirstName());
        user.setSurname(input.getSurname());
        user.setPatronymic(input.getPatronymic());
        user.setLogin(input.getUsername());
        user.setStatus(input.getUserStatus());
        user.setNumber(input.getNumber());
        user.setRole(input.getRole());
        user.setPosition(input.getPosition());
        user.setPastPosition(user.getPastPosition());
        user.setCabinetNum(user.getCabinetNum());
        user.setMail(input.getMail());
        address.setCity(input.getCity());
        address.setStreet(input.getStreet());
        address.setHouse(input.getHouse());
        address.setFlat(input.getFlat());
        address.setResidenceCountry(input.getResidenceCountry());
        passport.setPassportId(input.getPassportId());
        passport.setPassportNum(input.getPassportNum());
        passport.setCountry(input.getCountry());
        passport.setNationality(input.getNationality());

        try {
            passport.setBirthDate(LocalDate.parse(input.getBirthDate(), formatter));
            passport.setIssueDate(LocalDate.parse(input.getIssueDate(), formatter));
            passport.setExpireDate(LocalDate.parse(input.getExpireDate(), formatter));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Wrong date!");
        }

        passport.setSex(input.getSex());
        passport.setBirthCountry(input.getBirthCountry());

        repository.save(user);
        addressRepository.save(address);
        passportRepository.save(passport);

        return createUserProfileDTO(user, passport, address);
    }

    private UserProfileDTO createUserProfileDTO(User user, Passport passport, Address address) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setFirstName(user.getFirstName());
        userProfileDTO.setSurname(user.getSurname());
        userProfileDTO.setPatronymic(user.getPatronymic());
        userProfileDTO.setUsername(user.getLogin());
        userProfileDTO.setUserStatus(user.getStatus());
        userProfileDTO.setNumber(user.getNumber());
        userProfileDTO.setRole(user.getRole());
        userProfileDTO.setPosition(user.getPosition());
        userProfileDTO.setPastPosition(user.getPastPosition());
        userProfileDTO.setCabinetNum(user.getCabinetNum());
        userProfileDTO.setMail(user.getMail());
        userProfileDTO.setCity(address.getCity());
        userProfileDTO.setStreet(address.getStreet());
        userProfileDTO.setHouse(address.getHouse());
        userProfileDTO.setFlat(address.getFlat());
        userProfileDTO.setResidenceCountry(address.getResidenceCountry());
        userProfileDTO.setPassportId(passport.getPassportId());
        userProfileDTO.setPassportNum(passport.getPassportNum());
        userProfileDTO.setCountry(passport.getCountry());
        userProfileDTO.setNationality(passport.getNationality());
        userProfileDTO.setBirthDate(passport.getBirthDate().format(formatter));
        userProfileDTO.setSex(passport.getSex());
        userProfileDTO.setIssueDate(passport.getIssueDate().format(formatter));
        userProfileDTO.setExpireDate(passport.getExpireDate().format(formatter));
        userProfileDTO.setBirthCountry(passport.getBirthCountry());

        return  userProfileDTO;
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
