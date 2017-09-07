package com.itcompany.softwarestore.configuration;

/**
 * MySQL DB config.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
/*@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan("com.itcompany.softwarestore")
@EnableJpaRepositories(
        basePackages = "com.itcompany.softwarestore.dao.repository",
        entityManagerFactoryRef = "mySQLEntityManager",
        transactionManagerRef = "mySQLTransactionManager")*/
public class MySQLDatabaseConfiguration {

/*    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLDatabaseConfiguration.class);

    @Autowired
    private Environment env;

    @SuppressWarnings("duplicate")
    @Bean
    public DataSource mySQLDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(MYSQL_PREFIX + DRIVER_CLASS_NAME));
        dataSource.setUrl(env.getProperty(MYSQL_PREFIX + URL));
        dataSource.setUsername(env.getProperty(MYSQL_PREFIX + USERNAME));
        dataSource.setPassword(env.getProperty(MYSQL_PREFIX + PASSWORD));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mySQLEntityManager() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(mySQLDataSource());

        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(env.getProperty(PACKAGES_TO_SCAN));
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties(env));

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager mySQLTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mySQLEntityManager().getObject());

        return transactionManager;
    }

    @Bean
    public JdbcTemplate mySQLJbdcTemplate() {
        return new JdbcTemplate(mySQLDataSource());
    }*/

}

