package view;

import model.User;
import storage.api.IUserRepository;
import utils.api.IHashCreator;
import view.api.IAccountService;
import view.api.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class AccountService implements IAccountService {

    private final IUserRepository repository;
    private final IUserService userService;
    private final IHashCreator hashCreator;
    private final String CURRENT_USER = "currentUser";

    public AccountService(IUserRepository repository, IUserService userService, IHashCreator hashCreator) {
        this.repository = repository;
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
}
