package com.itcompany.softwarestore.configuration;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static com.itcompany.softwarestore.configuration.Constants.DRIVER_CLASS_NAME;
import static com.itcompany.softwarestore.configuration.Constants.MAIN_PROFILE;
import static com.itcompany.softwarestore.configuration.Constants.MYSQL_PREFIX;
import static com.itcompany.softwarestore.configuration.Constants.PACKAGES_TO_SCAN;
import static com.itcompany.softwarestore.configuration.Constants.PASSWORD;
import static com.itcompany.softwarestore.configuration.Constants.URL;
import static com.itcompany.softwarestore.configuration.Constants.USERNAME;
import static com.itcompany.softwarestore.configuration.Constants.getHibernateProperties;

/**
 * MySQL DB config.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Configuration
@Profile("main")
@EnableTransactionManagement
@PropertySource("classpath:mysql_db.properties")
@ComponentScan("com.itcompany.softwarestore")
@EnableJpaRepositories(
        basePackages = "com.itcompany.softwarestore.dao.repository",
        entityManagerFactoryRef = "mySQLEntityManager",
        transactionManagerRef = "mySQLTransactionManager")
public class MySQLDatabaseConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLDatabaseConfiguration.class);

    @Autowired
    private Environment env;

    @SuppressWarnings("duplicate")
    @Bean
    @Primary
    public DataSource mySQLDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(MYSQL_PREFIX + DRIVER_CLASS_NAME));
        dataSource.setUrl(env.getProperty(MYSQL_PREFIX + URL));
        dataSource.setUsername(env.getProperty(MYSQL_PREFIX + USERNAME));
        dataSource.setPassword(env.getProperty(MYSQL_PREFIX + PASSWORD));
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mySQLEntityManager() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(mySQLDataSource());

        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(env.getProperty(PACKAGES_TO_SCAN));
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties(env));

        return entityManagerFactoryBean;
    }

    @Bean
    @Primary
    public JpaTransactionManager mySQLTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mySQLEntityManager().getObject());

        return transactionManager;
    }

    @Bean
    @Primary
    public JdbcTemplate mySQLJbdcTemplate() {
        return new JdbcTemplate(mySQLDataSource());
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:mysql_db/master.xml");
        liquibase.setContexts("main");
        if (env.acceptsProfiles(MAIN_PROFILE)) {
            liquibase.setShouldRun(true);
        } else {
            liquibase.setShouldRun(false);
        }
        return liquibase;
    }

}

