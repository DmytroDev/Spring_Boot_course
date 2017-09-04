package com.itcompany.softwarestore.dao.repository;

import com.itcompany.softwarestore.dao.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Data JPA repository that provides basic CRUD operations. Implemented when context is being started up.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public interface CategoryRepository extends JpaRepository<Category, String> {

    /**
     * Finds all category names.
     *
     * @return list with all software categories.
     */
    @Query("select c.name from Category c")
    @Transactional
    List<String> getAllCategories();
}
