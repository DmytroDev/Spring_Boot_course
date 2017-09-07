package com.itcompany.softwarestore.configuration;

import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public class Constants {
    private Constants(){}

    public static final String PROP_HIBERNATE_DIALECT = "hibernate.dialect";
    public static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String PROP_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    public static final String PROP_HIBERNATE_USE_SQL_COMMENTS = "hibernate.useSqlComments";

    public static final String PACKAGES_TO_SCAN = "db.entitymanager.packages.to.scan";

    public static final String URL = "url";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String DRIVER_CLASS_NAME = "driver-class-name";

    public static final String H2_PREFIX = "softwarestore.ds_h2.";
    public static final String MYSQL_PREFIX = "softwarestore.ds_mysql.";

    public static final String EMBEDDED_DB_PROFILE = "embedded database";
    public static final String MAIN_PROFILE = "main";

    public static Properties getHibernateProperties(Environment env) {
        Properties properties = new Properties();
        properties.put(PROP_HIBERNATE_DIALECT, env.getProperty(PROP_HIBERNATE_DIALECT));
        properties.put(PROP_HIBERNATE_SHOW_SQL, env.getProperty(PROP_HIBERNATE_SHOW_SQL));
        properties.put(PROP_HIBERNATE_FORMAT_SQL, env.getProperty(PROP_HIBERNATE_FORMAT_SQL));
        properties.put(PROP_HIBERNATE_USE_SQL_COMMENTS, env.getProperty(PROP_HIBERNATE_USE_SQL_COMMENTS));

        return properties;
    }

}
