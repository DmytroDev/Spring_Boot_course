package com.itcompany.softwarestore.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static com.itcompany.softwarestore.configuration.Constants.BASE_PACKAGES;
import static com.itcompany.softwarestore.configuration.Constants.MAIN_PROFILE;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Configuration
@Profile(MAIN_PROFILE)
@EnableTransactionManagement
@PropertySource("classpath:mysql_db.properties")
@ComponentScan("com.itcompany.softwarestore")
@ConditionalOnClass(DataSource.class)
@EnableJpaRepositories(
        basePackages = "com.itcompany.softwarestore.dao.repository",
        entityManagerFactoryRef = "mySQLEntityManager",
        transactionManagerRef = "mySQLTransactionManager")
public class MySQLAutoConfiguration {

    @Autowired
    private Environment env;

    @Primary
    @Bean(name = "mySQLDataSource")
    @ConfigurationProperties(prefix = "softwarestore.ds_mysql")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "mySQLEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("mySQLDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages(BASE_PACKAGES).build();
    }

    @Primary
    @Bean(name = "mySQLTransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("mySQLEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    @Primary
    public JdbcTemplate mySQLJbdcTemplate() {
        return new JdbcTemplate(dataSource());
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
