package com.itcompany.softwarestore.service;

/**
 * Service that provide logic for scans files and saves they into database.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public interface InitService {
    /**
     * Scans files and saves they into database.
     *
     * @param path path to folder with images
     * @param pictureSize picture size
     */
    void scanFilesAndSaveToDB(String path, int pictureSize);
}
