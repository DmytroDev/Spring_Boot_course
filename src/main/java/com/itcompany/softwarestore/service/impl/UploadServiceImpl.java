package com.itcompany.softwarestore.service.impl;

import com.itcompany.softwarestore.dao.entity.Software;
import com.itcompany.softwarestore.dao.repository.CategoryRepository;
import com.itcompany.softwarestore.dao.repository.SoftwareRepository;
import com.itcompany.softwarestore.model.dto.FileInfo;
import com.itcompany.softwarestore.model.dto.TxtFileFields;
import com.itcompany.softwarestore.service.SoftwareEntityBuilder;
import com.itcompany.softwarestore.service.UploadService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 * {@link UploadService} implementation.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Service
public class UploadServiceImpl implements UploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Autowired
    private SoftwareRepository softwareRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SoftwareEntityBuilder softwareEntityBuilder;

    @Override
    public FileInfo parseZipFile(String packageName, String description, MultipartFile multipartFile, String categoryName)
            throws IllegalArgumentException {
        validateCategory(categoryName);
        LOGGER.info("Start parsing ZIP-file '{}' ...", multipartFile.getOriginalFilename());
        FileInfo fileInfo = new FileInfo();

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(multipartFile.getBytes());
             ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream)) {
            ZipEntry zipEntry;
            Map<String, byte[]> imgContentMap = new HashMap<>();

            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(TxtFileFields.TXT_FILE_SUFFIX)) {
                    fileInfo = getInfoFromTxtFile(zipEntry, zipInputStream);
                } else {
                    imgContentMap.put(zipEntry.getName(), IOUtils.toByteArray(zipInputStream));
                }
            }

            fileInfo.setImg128Content(imgContentMap.get(fileInfo.getImg128FileName()));
            fileInfo.setImg512Content(imgContentMap.get(fileInfo.getImg512FileName()));
            fileInfo.setPkgName(packageName);
            fileInfo.setDescription(description);
            fileInfo.setCategory(categoryName);
            LOGGER.info("'{}' successfully parsed.", multipartFile.getOriginalFilename());
        } catch (IOException e) {
            LOGGER.error("Unable to read ZIP-file '{}'.", multipartFile.getOriginalFilename());
        }
        fileInfo.setPkgName(packageName);
        fileInfo.setDescription(description);
        fileInfo.setCategory(categoryName);

        return fileInfo;
    }

    @Override
    public void saveSoftware(FileInfo fileInfo) {
        Software software = softwareEntityBuilder.build(fileInfo);
        softwareRepository.saveAndFlush(software);
        LOGGER.info("Software '{}' successfully saved into database.", fileInfo.getFileName());
    }

    private FileInfo getInfoFromTxtFile(final ZipEntry entry, InputStream is) throws IOException {
        FileInfo fileInfo = new FileInfo();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(TxtFileFields.DELIMITER);
            switch (row[0]) {
                case TxtFileFields.NAME_FIELD:
                    fileInfo.setFileName(row[1].trim());
                    break;
                case TxtFileFields.PACKAGE_FIELD:
                    fileInfo.setPkgName(row[1].trim());
                    break;
                case TxtFileFields.PICTURE_128_FIELD:
                    fileInfo.setImg128FileName(row[1].trim());
                    break;
                case TxtFileFields.PICTURE_512_FIELD:
                    fileInfo.setImg512FileName(row[1].trim());
                    break;
                default:
                    LOGGER.warn("Invalid row '{}' into file '{}'.", line, entry.getName());
            }
        }
        LOGGER.info("Information from '{}' successfully extracted.", entry.getName());
        return fileInfo;
    }

    private void validateCategory(String category) throws IllegalArgumentException {
        if (!categoryRepository.getAllCategories().contains(category)) {
            LOGGER.error("Invalid category name: '{}'", category);
            throw new IllegalArgumentException();
        }
    }
}
