package com.itcompany.softwarestore.service;

import com.itcompany.softwarestore.dao.entity.Software;

import java.util.List;

/**
 * Services that provides the necessary information for the formation of initial page.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public interface HomeService {

    /**
     * Gets {@link Software} by id.
     *
     * @param id software id.
     * @return {@link Software}
     */
    Software getSoftwareById(long id);

    /**
     * Gets all category names.
     *
     * @return {@link List} of category names.
     */
    List<String> getAllCategoryNames();

    /**
     * Gets top 10 software's by desc.
     *
     * @return {@link List} of the {@link Software}.
     */
    List<Software> getTop10SoftwareByDesc();

    /**
     * Gets all {@link Software} by category names.
     *
     * @param categoryName name of category
     * @return {@link List} of the {@link Software}.
     */
    List<Software> getSoftwareByCategory(String categoryName);
}
