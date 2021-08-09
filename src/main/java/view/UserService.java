package view;

import model.Address;
import model.Passport;
import model.Talon;
import model.User;
import model.dto.*;
import model.enums.Role;
import model.enums.Status;
import org.apache.commons.lang3.RandomStringUtils;
import storage.api.*;
import utils.api.IHashCreator;
import view.api.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Random;

public class UserService implements IUserService {

    private final IUserRepository repository;
    private final IPassportRepository passportRepository;
    private final IAddressRepository addressRepository;
    private final ITalonRepository talonRepository;
    private final IHashCreator hashCreator;
    private final IMedCardRepository medCardRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final String CURRENT_USER = "currentUser";

    public UserService(IUserRepository repository, IPassportRepository passportRepository,
                       IAddressRepository addressRepository, IHashCreator hashCreator, ITalonRepository talonRepository, IMedCardRepository medCardRepository) {
        this.repository = repository;
        this.passportRepository = passportRepository;
        this.addressRepository = addressRepository;
        this.hashCreator = hashCreator;
        this.talonRepository = talonRepository;
        this.medCardRepository = medCardRepository;
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
    public User getById(Integer id) {
        return this.repository.getById(id);
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
        user.setSalt(createSalt());
        user.setHash(createPasswordHashWithSalt("", user.getSalt()));
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

    /**
     * Update user data from profile page by user
     *
     * @param input data that will be saved
     * @param request request information for HTTP servlets
     * @throws IllegalArgumentException in case of errors in password or dates
     */
    @Override
    @Transactional
    public void update(UserDTO input, HttpServletRequest request) {
        User user = this.get(input.getUsername());
        Passport passport = passportRepository.getByUserId(user.getId());
        Address address = addressRepository.getByUserId(user.getId());
        LocalDate birthDate;
        LocalDate issueDate;
        LocalDate expireDate;

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
        user.setNumber(input.getNumber());
        user.setPastPosition(user.getPastPosition());
        user.setCabinetNum(user.getCabinetNum());
        user.setMail(input.getMail());
        user.setStatus(Status.UNVERIFIED);

        if (!(nullOrEmpty(input.getCurrentPassword()) & nullOrEmpty(input.getNewPassword())
                & nullOrEmpty(input.getRepeatPassword()))) {
            if (!user.getHash().equals(hashCreator.createHash(input.getCurrentPassword()).concat(user.getSalt()))) {
                throw new IllegalArgumentException("Entered password for user is incorrect!");
            } else if (!input.getNewPassword().equals(input.getRepeatPassword())) {
                throw new IllegalArgumentException("Repeated password is wrong!");
            } else if (nullOrEmpty(input.getNewPassword())) {
                throw new IllegalArgumentException("Password couldn't be empty!");
            }

            user.setHash(hashCreator.createHash(input.getCurrentPassword()).concat(user.getSalt()));
        }


        address.setCity(input.getCity());
        address.setStreet(input.getStreet());
        address.setHouse(input.getHouse());
        address.setFlat(input.getFlat());
        address.setResidenceCountry(input.getResidenceCountry());
        passport.setPassportId(input.getPassportId());
        passport.setPassportNum(input.getPassportNum());
        passport.setCountry(input.getCountry());
        passport.setNationality(input.getNationality());

        birthDate = checkAndParseDate(input.getBirthDate());
        issueDate = checkAndParseDate(input.getIssueDate());
        expireDate = checkAndParseDate(input.getExpireDate());

        passport.setBirthDate(birthDate);
        passport.setIssueDate(issueDate);
        passport.setExpireDate(expireDate);
        passport.setSex(input.getSex());
        passport.setBirthCountry(input.getBirthCountry());

        repository.save(user);
        addressRepository.save(address);
        passportRepository.save(passport);

        UserSession userSession = new UserSession(user.getLogin(), user.getId(), user.getDoctorId(), user.getRole());
        request.getSession().setAttribute(CURRENT_USER, userSession);
    }

    /**
     * Update user data by admin
     *
     * @param input data that will be saved
     * @return object with updated data
     */
    @Override
    @Transactional
    public UserProfileDTO update(UserProfileDTO input) {
        User user = this.get(input.getUsername());
        Passport passport = passportRepository.getByUserId(user.getId());
        Address address = addressRepository.getByUserId(user.getId());
        LocalDate birthDate;
        LocalDate issueDate;
        LocalDate expireDate;

        if (passport == null) {
            passport = new Passport(user.getId());
        }

        if (address == null) {
            address = new Address(user.getId());
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

        birthDate = checkAndParseDate(input.getBirthDate());
        issueDate = checkAndParseDate(input.getIssueDate());
        expireDate = checkAndParseDate(input.getExpireDate());

        passport.setBirthDate(birthDate);
        passport.setIssueDate(issueDate);
        passport.setExpireDate(expireDate);
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

        if (address != null) {
            userProfileDTO.setCity(address.getCity());
            userProfileDTO.setStreet(address.getStreet());
            userProfileDTO.setHouse(address.getHouse());
            userProfileDTO.setFlat(address.getFlat());
            userProfileDTO.setResidenceCountry(address.getResidenceCountry());
        }

        if (passport != null) {
            userProfileDTO.setPassportId(passport.getPassportId());
            userProfileDTO.setPassportNum(passport.getPassportNum());
            userProfileDTO.setCountry(passport.getCountry());
            userProfileDTO.setNationality(passport.getNationality());
            userProfileDTO.setBirthDate(passport.getBirthDate().format(formatter));
            userProfileDTO.setSex(passport.getSex());
            userProfileDTO.setIssueDate(passport.getIssueDate().format(formatter));
            userProfileDTO.setExpireDate(passport.getExpireDate().format(formatter));
            userProfileDTO.setBirthCountry(passport.getBirthCountry());
        }

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

    @Override
    public void delete(String username) {
        User user = this.get(username);

        if (user == null) {
            throw new IllegalArgumentException("User don't exist. Nothing to delete.");
        }

        List<Talon> talonList = talonRepository.findAllByUserId(user.getId());
        Passport passport = passportRepository.getByUserId(user.getId());
        Address address = addressRepository.getByUserId(user.getId());

        for (Talon talon : talonList) {
            talon.setUserId(null);
            talonRepository.save(talon);
        }

        if (passport != null) {
            passportRepository.delete(passport);
        }

        if (address != null) {
            addressRepository.delete(address);
        }

        repository.delete(user);
    }

    @Override
    @Transactional
    public List<MedCardWithUsername> getAllMedcardInfo(String login, HttpServletRequest request) {
        UserSession user = (UserSession) request.getSession().getAttribute(CURRENT_USER);
        return medCardRepository.findByUserIdOrderByDiagnoseDate(user.getUserId());
    }

    @Override
    @Transactional
    public List<Appointments> getAllTalonInfo(String login, HttpServletRequest request) {
        UserSession user = (UserSession) request.getSession().getAttribute(CURRENT_USER);
        return talonRepository.findByUserIdOrderByVisitTime(user.getUserId());
    }

    /**
     * Transforms String in LocalDate if possible
     *
     * @param date String containing date
     * @return LocalDate of String or null if String empty
     * @throws IllegalArgumentException if parsing is impossible
     */
    private LocalDate checkAndParseDate(String date) {
        LocalDate result = null;

        if (!date.isEmpty()) {
            try {
                result = LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Date is wrong!");
            }
        }

        return result;
    }
}
