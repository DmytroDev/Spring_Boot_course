package com.itcompany.softwarestore.configuration;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
import static com.itcompany.softwarestore.configuration.Constants.H2_PREFIX;
import static com.itcompany.softwarestore.configuration.Constants.PACKAGES_TO_SCAN;
import static com.itcompany.softwarestore.configuration.Constants.PASSWORD;
import static com.itcompany.softwarestore.configuration.Constants.URL;
import static com.itcompany.softwarestore.configuration.Constants.USERNAME;
import static com.itcompany.softwarestore.configuration.Constants.getHibernateProperties;

/**
 * H2 DB config.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan("com.itcompany.softwarestore")
@EnableJpaRepositories(
        basePackages = "com.itcompany.softwarestore.dao.repository",
        entityManagerFactoryRef = "h2EntityManager",
        transactionManagerRef = "h2TransactionManager")
public class H2DatabaseConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(H2DatabaseConfiguration.class);

    @Autowired
    private Environment env;

    @SuppressWarnings("duplicate")
    @Bean
    @Primary
    public DataSource h2DataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(H2_PREFIX + DRIVER_CLASS_NAME));
        dataSource.setUrl(env.getProperty(H2_PREFIX + URL));
        dataSource.setUsername(env.getProperty(H2_PREFIX + USERNAME));
        dataSource.setPassword(env.getProperty(H2_PREFIX + PASSWORD));
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean h2EntityManager() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(h2DataSource());

        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(env.getProperty(PACKAGES_TO_SCAN));
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties(env));

        return entityManagerFactoryBean;
    }

    @Bean
    @Primary
    public JpaTransactionManager h2TransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(h2EntityManager().getObject());

        return transactionManager;
    }

    @Bean
    public JdbcTemplate h2JbdcTemplate() {
        return new JdbcTemplate(h2DataSource());
    }

/*    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
        liquibase.setContexts("development, production");
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_FAST)) {
            if ("org.h2.jdbcx.JdbcDataSource".equals(propertyResolver.getProperty("dataSourceClassName"))) {
                liquibase.setShouldRun(true);
                log.warn("Using '{}' profile with H2 database in memory is not optimal, you should consider switching to" +
                        " MySQL or Postgresql to avoid rebuilding your database upon each start.", Constants.SPRING_PROFILE_FAST);
            } else {
                liquibase.setShouldRun(false);
            }
        } else {
            log.debug("Configuring Liquibase");
        }
        return liquibase;
    }*/

}
