package com.itcompany.softwarestore.model.dto;

import java.io.ByteArrayOutputStream;

/**
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public class ZipArchiveInfo {

    private String fileName;
    private ByteArrayOutputStream outputStream;

    public ZipArchiveInfo() {
    }

    public ZipArchiveInfo(String fileName, ByteArrayOutputStream outputStream) {
        this.fileName = fileName;
        this.outputStream = outputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ByteArrayOutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
