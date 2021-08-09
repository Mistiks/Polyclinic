package view;

import model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final UserDetailsService userDetailsService;

    /** Private constructor that defines implementation of IUserService interface */
    public AuthService(IUserService userService, IHashCreator hashCreator, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.hashCreator = hashCreator;
        this.userDetailsService = userDetailsService;
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
        UserDetails principal;

        try {
            principal = userDetailsService.loadUserByUsername(login);
        } catch (UsernameNotFoundException e) {
            return null;
        }

        User user = userService.get(login);

        if (!Objects.equals(user.getHash(), hashCreator.createHash(password.concat(user.getSalt())))) {
            return null;
        }

        setSecurityContext(principal);

        return user;
    }

    @Override
    public User googleAuthentication(String mail, String id) {
        User user = this.userService.getByGoogleId(id);
        UserDetails principal;

        if (user == null) {
            user = new User(mail, id, mail);
            this.userService.signUpGoogle(user);
            principal = userDetailsService.loadUserByUsername(user.getLogin());
            setSecurityContext(principal);
            return user;
        }

        principal = userDetailsService.loadUserByUsername(user.getLogin());
        setSecurityContext(principal);

        return user;
    }

    /**
     * Authenticates user in Spring Security
     *
     * @param principal UserDetails object with authenticated User object from database
     */
    private void setSecurityContext(UserDetails principal) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
    }
}
