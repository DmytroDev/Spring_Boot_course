package com.itcompany.softwarestore.configuration;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan("com.itcompany.softwarestore")
@EnableJpaRepositories(basePackages = "com.itcompany.softwarestore.dao.repository")
public class DatabaseConfiguration {

    private static final String PROP_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROP_HIBERNATE_MULTIPLE_LINES_SQL_COMMAND_EXTRACTOR = "hibernate.hbm2ddl.import_files_sql_extractor";

    private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROP_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROP_HIBERNATE_USE_SQL_COMMENTS = "hibernate.useSqlComments";
    private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";

    @Value("${db.driver}")
    private String dbDriver;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.name}")
    private String dbName;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.multipleLinesSqlCommandExtractor}")
    private String hibernateMultipleLinesSqlCommandExtractor;

    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;

    @Value("${hibernate.format_sql}")
    private String hibernateFormatSql;

    @Value("${hibernate.useSqlComments}")
    private String hibernateUseSqlComments;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;

    @Value("${db.entitymanager.packages.to.scan}")
    private String packagesToScan;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(packagesToScan);
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public JdbcTemplate getJbdcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(PROP_HIBERNATE_DIALECT, hibernateDialect);
        properties.put(PROP_HIBERNATE_SHOW_SQL, hibernateShowSql);
        properties.put(PROP_HIBERNATE_FORMAT_SQL, hibernateFormatSql);
        properties.put(PROP_HIBERNATE_USE_SQL_COMMENTS, hibernateUseSqlComments);
        properties.put(PROP_HIBERNATE_HBM2DDL_AUTO, hibernateHbm2ddlAuto);
        properties.put(PROP_HIBERNATE_MULTIPLE_LINES_SQL_COMMAND_EXTRACTOR, hibernateMultipleLinesSqlCommandExtractor);

        return properties;
    }

}
