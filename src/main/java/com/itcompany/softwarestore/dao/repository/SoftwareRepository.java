package com.itcompany.softwarestore.dao.repository;

import com.itcompany.softwarestore.dao.entity.Category;
import com.itcompany.softwarestore.dao.entity.Software;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Data JPA repository that provides basic CRUD operations. Implemented when context is being started up.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public interface SoftwareRepository extends JpaRepository<Software, Long> {

    /**
     * Save image (with size 128 x 128px) content for concrete {@link Software}.
     *
     * @param content image content as byte[].
     * @param applicationId software id.
     */
    @Modifying
    @Transactional
    @Query("update Software s "
            + "set s.pictureContent128 =:content "
            + "where s.id =:applicationId")
    void updatePictureContent128(@Param("content") byte[] content,
                                 @Param("applicationId") Long applicationId);

    /**
     * Save image (with size 512 x 512px) content for concrete {@link Software}.
     *
     * @param content image content as byte[].
     * @param applicationId software id.
     */
    @Modifying
    @Transactional
    @Query("update Software s "
            + "set s.pictureContent512 =:content "
            + "where s.id =:applicationId")
    void updatePictureContent512(@Param("content") byte[] content,
                                 @Param("applicationId") Long applicationId);

    /**
     * Updates {@link Software} as downloads number.
     *
     * @param applicationId software id
     */
    @Modifying
    @Transactional
    @Query("update Software s "
            + "set s.downloadsNumber = s.downloadsNumber + 1 "
            + "where s.id =:applicationId")
    void increaseDownloadNum(@Param("applicationId") Long applicationId);

    /**
     * Finds Top 5 {@link Software} ordered by downloads number.
     *
     * @return {@link List} of the  {@link Software}
     */
    @Transactional
    List<Software> findTop5ByOrderByDownloadsNumberDesc();

    /**
     * Finds Top 5 {@link Software} ordered by uploaded time.
     *
     * @return {@link List} of the  {@link Software}
     */
    @Transactional
    List<Software> findTop5ByOrderByTimeUploadedDesc();

    /**
     * Finds {@link Software} by {@link Category}.
     *
     * @param category {@link Category}.
     * @return {@link List} of the  {@link Software}
     */
    @Transactional
    List<Software> findByCategory(Category category);
}
