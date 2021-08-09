package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import storage.api.*;
import utils.HashCreator;
import utils.api.IHashCreator;
import view.*;
import view.api.*;

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
    public IUserService getUserService(IUserRepository repository, IPassportRepository passportRepository,
                                       IAddressRepository addressRepository, IHashCreator hashCreator,
                                       ITalonRepository talonRepository, IMedCardRepository medCardRepository) {
        return new UserService(repository, passportRepository, addressRepository, hashCreator, talonRepository, medCardRepository);
    }

    /**
     * Configuration for IAuthService interface
     *
     * @param userService instance of IUserService interface
     * @return object which implements IAuthService interface
     */
    @Bean
    public IAuthService getAuthService(IUserService userService, IHashCreator hashCreator, UserDetailsService userDetailsService) {
        return new AuthService(userService, hashCreator, userDetailsService);
    }

    @Bean
    public IHashCreator getHashCreator() {
        return new HashCreator();
    }

    @Bean
    public INewsService getNewsService(INewsRepository repository) {
        return new NewsService(repository);
    }

    @Bean
    public IAddressService getAddressService(IAddressRepository repository) {
        return new AddressService(repository);
    }

    @Bean
    public IPassportService getPassportService(IPassportRepository repository) {
        return new PassportService(repository);
    }

    @Bean
    public IDepartmentService getDepartmentService(IDepartmentRepository repository) {
        return new DepartmentService(repository);
    }

    @Bean
    public ICountryService getCountryService(ICountryRepository repository) {
        return new CountryService(repository);
    }

    @Bean
    public IDiseaseService getDiseaseService(IDiseaseRepository repository) {
        return new DiseaseService(repository);
    }

    @Bean
    public ISymptomService getSymptomService(ISymptomRepository repository) {
        return new SymptomService(repository);
    }

    @Bean
    public IMedCardService getMedCardService(IMedCardRepository repository, IUserService userService) {
        return new MedCardService(repository, userService);
    }

    @Bean
    public ITalonService getTalonService(ITalonRepository repository, IUserService userService) {
        return new TalonService(userService, repository);
    }
}