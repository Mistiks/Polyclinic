package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Class for creating connection to database
 *
 * @author Vadim Rataiko
 * @version  1.0
 */
@Configuration
@EnableJpaRepositories("storage")
@EnableTransactionManagement
public class PersistencyConfig {

    /**
     * Creates pool of connections
     *
     * @return A factory for connections to the physical data source that this object represents.
     * @throws PropertyVetoException is thrown when a proposed change to a
     * property represents an unacceptable value
     */
    @Bean
    public DataSource dataSource() throws PropertyVetoException {

        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("org.postgresql.Driver");
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/edu");
        cpds.setUser("postgres");
        cpds.setPassword("password");
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);

        return cpds;
    }

    /**
     * Configures EntityManagerFactory interface
     *
     * @param dataSource A factory for connections to the physical data source
     * @return a shared JPA EntityManagerFactory in a Spring application context
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("model");
        factory.setDataSource(dataSource);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.spatial.dialect.postgis.PostgisPG10Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("show_sql", "true");
        properties.setProperty("hibernate.default_schema", "polyclinic");

        factory.setJpaProperties(properties);
        return factory;
    }

    /**
     * Creates manager to create, commit, or rollback transactions.
     *
     * @param entityManagerFactory Interface used to interact with the entity manager factory
     * for the persistence unit
     * @return the central interface in Spring's imperative transaction infrastructure
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}

