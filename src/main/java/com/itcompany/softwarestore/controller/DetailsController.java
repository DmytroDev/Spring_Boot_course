package com.itcompany.softwarestore.controller;

import com.itcompany.softwarestore.dao.entity.Software;
import com.itcompany.softwarestore.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * REST controller to handle Http request for receiving data for details-page.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Controller
public class DetailsController {

    @Autowired
    private HomeService homeService;

    /**
     * REST endpoint to get software id and details-page as ModelAndView.
     *
     * @param id software id
     * @return {@link ModelAndView}
     */
    @GetMapping("/view/details/{id}")
    public ModelAndView getDetails(@PathVariable long id) {
        ModelAndView mav = new ModelAndView("pages/details-page");
        Software software = homeService.getSoftwareById(id);
        mav.addObject("software", software);
        return mav;
    }

    /**
     * REST endpoint to get all software's for concrete category and software-by-category as ModelAndView.
     *
     * @param categoryName software category
     * @return {@link ModelAndView}
     */
    @GetMapping(value = "/view/category/{categoryName}")
    public ModelAndView getSoftwareByCategory(@PathVariable String categoryName) {
        ModelAndView mav = new ModelAndView("fragments/software-by-category");
        List<Software> softwareList = homeService.getSoftwareByCategory(categoryName);
        mav.addObject("softwareList", softwareList);
        return mav;
    }

}
