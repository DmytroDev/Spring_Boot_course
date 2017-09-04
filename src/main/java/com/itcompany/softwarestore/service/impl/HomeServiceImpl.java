package com.itcompany.softwarestore.service.impl;

import com.itcompany.softwarestore.dao.entity.Category;
import com.itcompany.softwarestore.dao.entity.Software;
import com.itcompany.softwarestore.dao.repository.CategoryRepository;
import com.itcompany.softwarestore.dao.repository.SoftwareRepository;
import com.itcompany.softwarestore.service.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link HomeService} implementation.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Service
public class HomeServiceImpl implements HomeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeServiceImpl.class);

    @Autowired
    private SoftwareRepository softwareRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Software getSoftwareById(long id) {
        Software software = softwareRepository.findOne(id);
        LOGGER.info("Successfully got Software '{}'.", software);
        return software;
    }

    @Override
    public List<String> getAllCategoryNames() {
        List<String> categoryNames = categoryRepository.getAllCategories();
        LOGGER.info("All Category names has been successfully extracted from the database. List size '{}'.", categoryNames.size());
        return categoryNames;
    }

    @Override
    public List<Software> getTop10SoftwareByDesc() {
        List<Software> softwareEntities = softwareRepository.findTop5ByOrderByDownloadsNumberDesc();
        softwareEntities.addAll(softwareRepository.findTop5ByOrderByTimeUploadedDesc());
        LOGGER.info("Successfully got Top 10 Software '{}'.", softwareEntities);
        return softwareEntities;
    }

    @Override
    public List<Software> getSoftwareByCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);

        List<Software> softwareEntities = softwareRepository.findByCategory(category);
        LOGGER.info("Successfully got Software's by name '{}'.", softwareEntities);
        return softwareEntities;
    }

}
