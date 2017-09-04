package com.itcompany.softwarestore.controller;

import com.itcompany.softwarestore.dao.entity.Software;
import com.itcompany.softwarestore.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * REST controller to handle Http request for receiving data for initial page.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Controller
public class LandingController {

    private static final String DEFAULT_NAME = "guest";

    @Autowired
    private HomeService homeService;

    /**
     * REST endpoint for redirect after authorization to home page.
     *
     * @param error error message in case wrong authorization
     * @return {@link ModelAndView}
     */
    @GetMapping(value = {"/", "/index"})
    public ModelAndView home(@RequestParam(value = "error", required = false) String error) {

        ModelAndView view = new ModelAndView("home/index");
        if (error != null) {
            view.addObject("error", "Invalid username or password!");
        } else {
            List<Software> softwareTop10 = homeService.getTop10SoftwareByDesc();
            view.addObject("softwareList", softwareTop10);
            List<String> categories = homeService.getAllCategoryNames();
            view.addObject("categories", categories);
        }
        return view;
    }

    /**
     * REST endpoint for getting image (size 128 x 128) by software id.
     *
     * @param id software id
     * @return {@link ResponseEntity} image as byte array.
     */
    @GetMapping(value = "/imgController128/getImg/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage128(@PathVariable long id) {
        Software software = homeService.getSoftwareById(id);
        byte[] image128 = software.getPictureContent128();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(image128, headers, HttpStatus.CREATED);
    }

    /**
     * REST endpoint for getting image (size 512 x 512) by software id.
     *
     * @param id software id
     * @return {@link ResponseEntity} image as byte array.
     */
    @GetMapping(value = "/imgController512/getImg/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage512(@PathVariable long id) {
        Software software = homeService.getSoftwareById(id);
        byte[] image512 = software.getPictureContent512();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(image512, headers, HttpStatus.CREATED);
    }

    /**
     * REST endpoint for redirect to all-software page.
     *
     * @return {@link ModelAndView}
     */
    @GetMapping(value = "/view/index")
    public ModelAndView toAllSoftwarePage() {
        ModelAndView view = new ModelAndView("pages/all-software");
        List<String> categories = homeService.getAllCategoryNames();
        view.addObject("categories", categories);
        return view;
    }

    /**
     * REST endpoint for update user name in page-header.
     *
     * @param username user name
     * @return {@link ModelAndView}
     */
    @GetMapping(value = "/view/updateUser/{username}")
    public ModelAndView updateUser(@PathVariable(value = "username", required = false) String username) {

        ModelAndView view = new ModelAndView("fragments/header-top");
        if (username == null || username.isEmpty()) {
            view.addObject("username", DEFAULT_NAME);
        } else {
            view.addObject("username", username);
        }
        return view;
    }

}
