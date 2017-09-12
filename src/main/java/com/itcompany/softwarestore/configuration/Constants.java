package com.itcompany.softwarestore.configuration;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public class Constants {
    private Constants(){}

    public static final String BASE_PACKAGES = "com.itcompany.softwarestore.dao.entity";

    // Profiles
    public static final String EMBEDDED_DB_PROFILE = "embedded database";
    public static final String MAIN_PROFILE = "main";

    // Web
    public static final String VIEWS_LOCATION = "/WEB-INF/views/";
    public static final String SUFFIX = ".jsp";
    public static final String RESOURCES_LOCATION = "/resources/";
    public static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";

}
