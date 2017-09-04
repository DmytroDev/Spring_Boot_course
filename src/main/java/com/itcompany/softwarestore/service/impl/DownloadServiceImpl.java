package com.itcompany.softwarestore.service.impl;

import com.itcompany.softwarestore.dao.entity.Software;
import com.itcompany.softwarestore.dao.repository.SoftwareRepository;
import com.itcompany.softwarestore.model.dto.ZipArchiveInfo;
import com.itcompany.softwarestore.service.DownloadService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.itcompany.softwarestore.model.dto.TxtFileFields.DELIMITER;
import static com.itcompany.softwarestore.model.dto.TxtFileFields.LINE_SEPARATOR;
import static com.itcompany.softwarestore.model.dto.TxtFileFields.NAME_FIELD;
import static com.itcompany.softwarestore.model.dto.TxtFileFields.PACKAGE_FIELD;
import static com.itcompany.softwarestore.model.dto.TxtFileFields.PICTURE_128_FIELD;
import static com.itcompany.softwarestore.model.dto.TxtFileFields.PICTURE_512_FIELD;

/**
 * {@link DownloadService} implementation.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Service
public class DownloadServiceImpl implements DownloadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadServiceImpl.class);
    private static final String SUFFIX = ".zip";
    private static final String IMG_128_NAME = "128.png";
    private static final String IMG_512_NAME = "512.png";
    private static final String DEFAULT_IMG_PATH_128 = "/images/default/no_image_available_128.png";
    private static final String DEFAULT_IMG_PATH_512 = "/images/default/no_image_available_512.png";

    @Autowired
    private SoftwareRepository repository;

    @Override
    public ZipArchiveInfo createZipArchive(Long softwareId) {
        Software software = repository.findOne(softwareId);
        ZipArchiveInfo zipArchive = null;

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ZipOutputStream out = new ZipOutputStream(byteArrayOutputStream)) {

            out.putNextEntry(new ZipEntry(IMG_128_NAME));
            byte[] imgContent128 = Optional.ofNullable(software.getPictureContent128())
                    .orElse(getDefaultImgByteArray(DEFAULT_IMG_PATH_128));

            out.write(imgContent128);
            out.closeEntry();

            out.putNextEntry(new ZipEntry(IMG_512_NAME));
            byte[] imgContent512 = Optional.ofNullable(software.getPictureContent512())
                    .orElse(getDefaultImgByteArray(DEFAULT_IMG_PATH_512));

            out.write(imgContent512);
            out.closeEntry();

            out.putNextEntry(new ZipEntry(software.getName() + ".txt"));
            String txtFileContent = writeContentToTxtFile(software);
            out.write(txtFileContent.getBytes());
            out.closeEntry();
            LOGGER.info("ZIP archive was successfully created for software with id '{}'.", softwareId);

            zipArchive = new ZipArchiveInfo(software.getName() + SUFFIX, byteArrayOutputStream);
        } catch (IOException ex) {
            LOGGER.error("Unable create ZIP archive for software with id '{}'.", softwareId);
        }
        return zipArchive;
    }

    @Override
    public void increaseDownloadNum(Long id) {
        repository.increaseDownloadNum(id);
        LOGGER.info("Download number was successfully increased for Software with id  '{}'.", id);
    }

    private String writeContentToTxtFile(Software software) throws IOException {
        StringJoiner stringJoiner = new StringJoiner(LINE_SEPARATOR);
        stringJoiner.add(NAME_FIELD + DELIMITER + software.getName());
        stringJoiner.add(PACKAGE_FIELD + DELIMITER + software.getAppPackage());
        stringJoiner.add(PICTURE_128_FIELD + DELIMITER + IMG_128_NAME);
        stringJoiner.add(PICTURE_512_FIELD + DELIMITER + IMG_512_NAME);
        return stringJoiner.toString();
    }

    private byte[] getDefaultImgByteArray(String path) {
        byte[] imgByteArray = null;
        try (InputStream resourceAsStream = this.getClass().getResourceAsStream(path)) {
            imgByteArray = IOUtils.toByteArray(resourceAsStream);
        } catch (IOException e) {
            LOGGER.error("Unable to reading file with default image.");
        }
        return imgByteArray;
    }

}
