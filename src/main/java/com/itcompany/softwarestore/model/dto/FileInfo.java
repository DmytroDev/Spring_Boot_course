package com.itcompany.softwarestore.model.dto;

public class FileInfo {
    private String fileName;
    private String pkgName;
    private String img128FileName;
    private String img512FileName;
    private byte[] img128Content;
    private byte[] img512Content;
    private String description;
    private String category;
    private Long uploadTime;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getImg128FileName() {
        return img128FileName;
    }

    public void setImg128FileName(String img128FileName) {
        this.img128FileName = img128FileName;
    }

    public String getImg512FileName() {
        return img512FileName;
    }

    public void setImg512FileName(String img512FileName) {
        this.img512FileName = img512FileName;
    }

    public byte[] getImg128Content() {
        return img128Content;
    }

    public void setImg128Content(byte[] img128Content) {
        this.img128Content = img128Content;
    }

    public byte[] getImg512Content() {
        return img512Content;
    }

    public void setImg512Content(byte[] img512Content) {
        this.img512Content = img512Content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }
}
