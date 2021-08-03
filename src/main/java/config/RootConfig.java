package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import storage.api.*;
import utils.HashCreator;
import utils.JsonWriter;
import utils.api.IHashCreator;
import utils.api.IJsonWriter;
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
                                       IAddressRepository addressRepository, IHashCreator hashCreator, ITalonRepository talonRepository) {
        return new UserService(repository, passportRepository, addressRepository, hashCreator, talonRepository);
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

    @Bean
    public IJsonWriter getJsonWriter() {
        return new JsonWriter();
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
    public IAccountService getAccountService(IUserService userService, IUserRepository repository,
                                             IHashCreator hashCreator, IAddressRepository addressRepository,
                                             IPassportRepository passportRepository) {
        return new AccountService(repository, addressRepository, passportRepository, userService, hashCreator);
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
    public IMedCardService getMedCardService(IMedCardRepository repository) {
        return new MedCardService(repository);
    }

    @Bean
    public ITalonService getTalonService(ITalonRepository repository, IUserService userService) {
        return new TalonService(userService, repository);
    }
}