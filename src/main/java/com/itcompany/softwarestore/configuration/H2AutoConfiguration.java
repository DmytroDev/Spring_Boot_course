package com.itcompany.softwarestore.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static com.itcompany.softwarestore.configuration.Constants.BASE_PACKAGES;
import static com.itcompany.softwarestore.configuration.Constants.EMBEDDED_DB_PROFILE;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Configuration
@Profile(EMBEDDED_DB_PROFILE)
@EnableTransactionManagement
@PropertySource("classpath:h2_db.properties")
@ComponentScan("com.itcompany.softwarestore")
@ConditionalOnClass({DataSource.class, EmbeddedDatabaseType.class})
@EnableJpaRepositories(
        basePackages = "com.itcompany.softwarestore.dao.repository",
        entityManagerFactoryRef = "h2EntityManager",
        transactionManagerRef = "h2TransactionManager")
public class H2AutoConfiguration {

    @Autowired
    private Environment env;

    @Bean(name = "h2DataSource")
    @ConfigurationProperties(prefix = "softwarestore.ds_h2")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "h2EntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("h2DataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages(BASE_PACKAGES).build();
    }

    @Bean(name = "h2TransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("h2EntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public JdbcTemplate h2JbdcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:h2_db/master.xml");
        liquibase.setContexts("embedded database");
        if (env.acceptsProfiles(EMBEDDED_DB_PROFILE)) {
            liquibase.setShouldRun(true);
        } else {
            liquibase.setShouldRun(false);
        }
        return liquibase;
    }

}

