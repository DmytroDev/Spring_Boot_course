package com.itcompany.softwarestore.service;

import com.itcompany.softwarestore.model.dto.FileInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * File upload service.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public interface UploadService {

    /**
     * Parses Zip-archive into {@link FileInfo}.
     *
     * @param packageName package name
     * @param description software description
     * @param multipartFile {@link MultipartFile} Zip-archive
     * @param categoryName software category name
     * @return {@link FileInfo}
     */
    FileInfo parseZipFile(String packageName, String description, MultipartFile multipartFile, String categoryName);

    /**
     * Saves {@link FileInfo} into database.
     *
     * @param fileInfo {@link FileInfo}
     */
    void saveSoftware(FileInfo fileInfo);
}
