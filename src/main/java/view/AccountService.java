package view;

import model.Address;
import model.Passport;
import model.User;
import model.enums.Gender;
import storage.api.IAddressRepository;
import storage.api.IPassportRepository;
import storage.api.IUserRepository;
import utils.api.IHashCreator;
import view.api.IAccountService;
import view.api.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class AccountService implements IAccountService {

    private final IUserRepository repository;
    private final IAddressRepository addressRepository;
    private final IPassportRepository passportRepository;
    private final IUserService userService;
    private final IHashCreator hashCreator;
    private final String CURRENT_USER = "currentUser";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AccountService(IUserRepository repository, IAddressRepository addressRepository, IPassportRepository passportRepository, IUserService userService, IHashCreator hashCreator) {
        this.repository = repository;
        this.addressRepository = addressRepository;
        this.passportRepository = passportRepository;
        this.userService = userService;
        this.hashCreator = hashCreator;
    }

    public void updateGeneralInfo(HashMap<String, String> info, HttpServletRequest request) {
        User user = userService.get(info.get("username"));

        if (user != null) {
            throw new IllegalArgumentException("User already exists!");
        } else if (info.get("username").isEmpty()) {
            throw new IllegalArgumentException("Username couldn't be empty!");
        }

        user = (User) request.getSession().getAttribute(CURRENT_USER);
        user.setLogin(info.get("username"));
        user.setFirstName(info.get("name"));
        user.setSurname(info.get("surname"));
        user.setMail(info.get("mail"));
        user.setNumber(info.get("number"));
        repository.save(user);
        request.getSession().setAttribute(CURRENT_USER, user);
    }

    @Override
    public void updatePassword(HashMap<String, String> info, HttpServletRequest request) {
       User user = (User) request.getSession().getAttribute(CURRENT_USER);

       if (!user.getHash().equals(hashCreator.createHash(info.get("currentPassword").concat(user.getSalt())))) {
           throw new IllegalArgumentException("Entered password for user is incorrect!");
       } else if (!info.get("newPassword").equals(info.get("repeatPassword"))) {
           throw new IllegalArgumentException("Repeated password is wrong!");
       } else if (info.get("newPassword").isEmpty()) {
           throw new IllegalArgumentException("Password couldn't be empty!");
       }

       user.setHash(hashCreator.createHash(info.get("newPassword").concat(user.getSalt())));
       repository.save(user);
       request.getSession().setAttribute(CURRENT_USER, user);
    }

    @Override
    public void updatePassport(HashMap<String, String> info, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(CURRENT_USER);
        String gender = "";

        if (info.get("sex").equals("Male")) {
            gender = Gender.MALE.getGender();
        } else if (info.get("sex").equals("Feale")) {
            gender = Gender.FEMALE.getGender();
        }

        Passport passport = passportRepository.getByUserId(user.getId());

        LocalDate issueDate;
        LocalDate expireDate;
        LocalDate birthDate;

        try {
            issueDate = LocalDate.parse(info.get("issueDate"), formatter);
            expireDate = LocalDate.parse(info.get("expireDate"), formatter);
            birthDate = LocalDate.parse(info.get("birthDate"), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Wrong date input!");
        }


        if (passport == null) {
            passport = new Passport(user.getId(), info.get("passportId"), info.get("passportNum"),
                    info.get("passportCountry"), info.get("nationality"), birthDate,
                    gender, issueDate, expireDate, info.get("birthCountry"));
        } else {
            passport.setPassportId(info.get("passportId"));
            passport.setPassportNum(info.get("passportNum"));
            passport.setCountry(info.get("passportCountry"));
            passport.setNationality(info.get("nationality"));
            passport.setSex(gender);
            passport.setBirthDate(birthDate);
            passport.setIssueDate(issueDate);
            passport.setExpireDate(expireDate);
            passport.setBirthCountry(info.get("birthCountry"));
        }

        passportRepository.save(passport);
    }

    @Override
    public void updateAddress(HashMap<String, String> info, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(CURRENT_USER);

        Address address = addressRepository.getByUserId(user.getId());

        if (address == null) {
            address = new Address(user.getId(), info.get("city"), info.get("street"),
                    info.get("house"), info.get("flat"), info.get("residenceCountry"));
        } else {
            address.setCity(info.get("city"));
            address.setStreet(info.get("street"));
            address.setHouse(info.get("house"));
            address.setFlat(info.get("flat"));
            address.setResidenceCountry(info.get("residenceCountry"));
        }

        addressRepository.save(address);
    }
}
