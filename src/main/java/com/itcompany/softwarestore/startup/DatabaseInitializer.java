package com.itcompany.softwarestore.startup;

import com.itcompany.softwarestore.service.InitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Scans and saves into database images when application has started up.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Component
public class DatabaseInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Autowired
    private InitService initService;

    @Value("${file_128.packages.to.scan}")
    private String file128Location;

    @Value("${file_512.packages.to.scan}")
    private String file512Location;

    private final int pictureSize128 = 128;
    private final int pictureSize512 = 512;

    /**
     * Scans and saves into database images when application has started up.
     */
    @EventListener(ContextRefreshedEvent.class)
    public void populate() {
        LOGGER.info("Started populate data into database ...");
        initService.scanFilesAndSaveToDB(file128Location, pictureSize128);
        initService.scanFilesAndSaveToDB(file512Location, pictureSize512);
        LOGGER.info("Finished populate data into database.");
    }
}
