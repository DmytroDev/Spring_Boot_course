package com.itcompany.softwarestore.aspect;

import com.itcompany.softwarestore.model.dto.FileInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Component for measuring the time of file upload.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Aspect
@Component
public class PerformanceAroundAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceAroundAspect.class);

    /**
     * Measures the time of file upload.
     *
     * @param proceedingJoinPoint {@link ProceedingJoinPoint}
     * @return {@link Object}
     */
    @Around("execution(* com.itcompany.softwarestore.service.UploadService.parseZipFile(..))")
    public Object parseZipFileAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.currentTimeMillis();
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            LOGGER.error(e.getMessage());
        }
        long uploadTime = System.currentTimeMillis() - startTime;
        LOGGER.info("Upload time is '{}' ms", uploadTime);
        FileInfo fileInfo = (FileInfo) value;
        fileInfo.setUploadTime(uploadTime);
        return fileInfo;
    }
}
