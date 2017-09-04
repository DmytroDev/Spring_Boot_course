package com.itcompany.softwarestore.controller;

import com.itcompany.softwarestore.model.dto.FileInfo;
import com.itcompany.softwarestore.model.dto.ZipArchiveInfo;
import com.itcompany.softwarestore.service.DownloadService;
import com.itcompany.softwarestore.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

/**
 * REST controller to handle Http request for upload/download files.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Controller
public class LoadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadController.class);

    @Autowired
    private DownloadService downloadService;

    @Autowired
    private UploadService uploadService;

    /**
     * REST endpoint for redirection to upload page.
     *
     * @return upload page.
     */
    @GetMapping("/view/upload")
    public String redirectToUploadPage() {
        return "pages/upload-form";
    }

    /**
     * REST endpoint for upload software.
     *
     * @param name         software name
     * @param description  software description
     * @param packageName  package name
     * @param categoryName category name
     * @param file         {@link MultipartFile} ZIP-archive
     * @return {@link ModelAndView}
     */
    @PostMapping("/view/upload")
    public ModelAndView doUpload(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "description", required = false) String description,
                                 @RequestParam(value = "packageName", required = false) String packageName,
                                 @RequestParam(value = "categoryName", required = false) String categoryName,
                                 @RequestParam(value = "file") MultipartFile file) {

        LOGGER.info("Running download file '{}'.", file.getOriginalFilename());
        ModelAndView mav = new ModelAndView("pages/uploadStatus");
        String msg = null;
        if (file.isEmpty()) {
            msg = "Please select a not empty file to upload";
            LOGGER.warn("Upload file is empty");
        } else {
            FileInfo fileInfo = uploadService.parseZipFile(packageName, description, file, categoryName);
            uploadService.saveSoftware(fileInfo);
            msg = "You successfully uploaded file: " + file.getOriginalFilename();
            LOGGER.info("File '{}' successfully uploaded.", name);
        }
        mav.addObject("msg", msg);
        return mav;
    }

    /**
     * REST endpoint for redirection to upload-status page.
     *
     * @return upload-status page.
     */
    @GetMapping("/view/uploadStatus")
    public String uploadStatus() {
        return "pages/uploadStatus";
    }

    /**
     * REST endpoint to download software by id.
     *
     * @param softwareId software id
     * @return {@link ResponseEntity}
     * @throws FileNotFoundException thrown if file which should be download not found
     */
    @GetMapping(value = "/download/archive/{softwareId}")
    public ResponseEntity<InputStreamResource> doDownload(@PathVariable long softwareId) throws FileNotFoundException {
        LOGGER.info("Start download software. Id = '{}'.", softwareId);
        ZipArchiveInfo zipArchiveInfo = downloadService.createZipArchive(softwareId);
        byte[] file = zipArchiveInfo.getOutputStream().toByteArray();

        final HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        header.setContentDispositionFormData("attachment", zipArchiveInfo.getFileName());
        header.setContentLength(file.length);
        InputStreamResource body = new InputStreamResource(new ByteArrayInputStream(file));
        downloadService.increaseDownloadNum(softwareId);
        LOGGER.info("Archive with '{}' successfully downloaded.", zipArchiveInfo.getFileName());

        return new ResponseEntity<>(body, header, HttpStatus.OK);
    }
}
