package config;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import view.CustomUserDetailsService;
import view.api.IUserService;

@Configuration
@EnableWebSecurity
@PropertySource("file:D:/Programs/Java_course/Polyclinic/src/main/resources/application.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";
    private static List<CommonOAuth2Provider> clients = Arrays.asList(
            CommonOAuth2Provider.GOOGLE
    );

    @Autowired
    Environment env;

    @Bean
    public UserDetailsService userDetailsService(IUserService userService) {
        return new CustomUserDetailsService(userService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(Environment env) {
        List<ClientRegistration> registrations = clients.stream()
                .map(c -> getRegistration(c, env))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository(env));
    }

    private ClientRegistration getRegistration(CommonOAuth2Provider client, Environment env) {

        String clientName = client.name().toLowerCase();
        String clientId = env.getProperty(CLIENT_PROPERTY_KEY + clientName + ".client-id");

        if (clientId == null) {
            return null;
        }

        String clientSecret = env.getProperty(CLIENT_PROPERTY_KEY + clientName + ".client-secret");

        if (clientSecret == null) {
            return null;
        }

        String callbackUrl = env.getProperty(CLIENT_PROPERTY_KEY + clientName + ".callback-url");

        if (callbackUrl == null) {
            return null;
        }

        String scopes = env.getProperty(CLIENT_PROPERTY_KEY + clientName + ".scopes");

        if (scopes == null) {
            return null;
        }

        return client.getBuilder(clientSecret)
                .redirectUri(callbackUrl)
                .scope(scopes.split(","))
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
                .and().oauth2Login()
                .loginPage("/signIn")
                .failureUrl("/signIn?error=true")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/home")
                .and().exceptionHandling()
                .accessDeniedPage("/home")
                .and().authorizeRequests()
                .antMatchers("/", "/home", "/signUp/**", "/signIn/**").permitAll()
                .antMatchers("/dashboard/**").hasAuthority(Role.ADMIN.name())
                .antMatchers("/doctorDashboard/**").hasAuthority(Role.DOCTOR.name())
                .antMatchers(HttpMethod.POST, "/talon/time").hasAnyAuthority(Role.ADMIN.name(), Role.DOCTOR.name(), Role.USER.name())
                .antMatchers( "/profile/**").hasAnyAuthority(Role.ADMIN.name(), Role.DOCTOR.name(), Role.USER.name())
        ;
    }
}
