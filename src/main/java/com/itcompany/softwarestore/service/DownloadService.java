package com.itcompany.softwarestore.service;

import com.itcompany.softwarestore.model.dto.ZipArchiveInfo;

/**
 * File download service.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public interface DownloadService {

    /**
     * Creates Zip-archive.
     *
     * @param softwareId software id.
     * @return {@link ZipArchiveInfo}
     */
    ZipArchiveInfo createZipArchive(Long softwareId);

    /**
     * Increases download number for concrete {@link com.itcompany.softwarestore.dao.entity.Software}.
     *
     * @param id increased download number.
     */
    void increaseDownloadNum(Long id);
}
