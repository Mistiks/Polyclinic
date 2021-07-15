package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import storage.api.IPasswordRepository;
import storage.api.IUserRepository;
import utils.HashCreator;
import utils.api.IHashCreator;
import view.AuthService;
import view.UserService;
import view.api.IAuthService;
import view.api.IUserService;

/**
 * Defining configuration for user's non-web components
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
@Configuration
@ComponentScan("config")
public class RootConfig {

    /**
     * Configuration for IUserService interface
     *
     * @param repository instance of IUserRepository interface
     * @return object which implements IUserService interface
     */
    @Bean
    public IUserService getUserService(IUserRepository repository, IPasswordRepository passwordRepository,
                                       IHashCreator hashCreator) {
        return new UserService(repository, passwordRepository, hashCreator);
    }

    /**
     * Configuration for IAuthService interface
     *
     * @param userService instance of IUserService interface
     * @return object which implements IAuthService interface
     */
    @Bean
    public IAuthService getAuthService(IUserService userService, IHashCreator hashCreator) {
        return new AuthService(userService, hashCreator);
    }

    @Bean
    public IHashCreator getHashCreator() {
        return new HashCreator();
    }
}