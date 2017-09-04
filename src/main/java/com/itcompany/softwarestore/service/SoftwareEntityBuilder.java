package com.itcompany.softwarestore.service;

import com.itcompany.softwarestore.dao.entity.Software;
import com.itcompany.softwarestore.model.dto.FileInfo;

/**
 * Services that provides build {@link Software} for further saving into database.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public interface SoftwareEntityBuilder {

    /**
     * Builds {@link Software} for further saving into database.
     *
     * @param fileInfo {@link FileInfo}
     * @return {@link Software}
     */
    Software build(FileInfo fileInfo);
}
