package com.itcompany.softwarestore.service.impl;

import com.itcompany.softwarestore.dao.repository.SoftwareRepository;
import com.itcompany.softwarestore.service.InitService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link InitService} implementation.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Service
public class InitServiceImpl implements InitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitServiceImpl.class);
    private static final String MASK = "**/*.png";
    private static final int PICTURE_SIZE_128 = 128;
    private static final int PICTURE_SIZE_512 = 512;

    @Autowired
    private SoftwareRepository softwareRepository;

    @Override
    public void scanFilesAndSaveToDB(String directoryPath, int pictureSize) {
        List<String> files = scanFolder(directoryPath + MASK);
        files.stream().forEach(fileName ->
                saveImageToDB(directoryPath + fileName, Long.valueOf(fileName.substring(0, fileName.indexOf("."))), pictureSize));
        LOGGER.info("Saving images from '{}' to Database completed", directoryPath);
    }

    private List<String> scanFolder(String directoryPath) {
        LOGGER.info("Start scan directory '{}' ", directoryPath);
        List<String> fileNames = null;
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources(directoryPath);
            fileNames = Arrays.stream(resources).map(resource -> resource.getFilename()).collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Unable to scan files into folder '{}' ", directoryPath);
        }
        LOGGER.info("Directory '{}' successfully scanned", directoryPath);
        return fileNames;
    }

    private void saveImageToDB(String fileName, Long rowId, int pictureSize) {
        byte[] content = readData(fileName);
        if (pictureSize == PICTURE_SIZE_128) {
            softwareRepository.updatePictureContent128(content, rowId);
        } else if (pictureSize == PICTURE_SIZE_512) {
            softwareRepository.updatePictureContent512(content, rowId);
        } else {
            LOGGER.error("Unsupported picture size. Size: '{}' ", pictureSize);
            throw new IllegalStateException("Unsupported picture size. Size: " + pictureSize);
        }
    }

    private byte[] readData(String fileName) {
        byte[] content = null;
        try {
            content = IOUtils.toByteArray(getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            LOGGER.error("Unable to read data from source '{}'", fileName);
        }
        return content;
    }

}
