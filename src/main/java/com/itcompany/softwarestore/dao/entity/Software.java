package com.itcompany.softwarestore.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * JPA entity that is mapped to the table SOFTWARE. Stores the following information:
 * <ul>
 * <li>software id;</li>
 * <li>software name;</li>
 * <li>package name;</li>
 * <li>content for images with size 128 x 128;</li>
 * <li>content for images with size 512 x 512;</li>
 * <li>software description;</li>
 * <li>software category name;</li>
 * <li>quantity of downloads;</li>
 * <li>uploaded time;</li>
 * </ul>
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "SOFTWARE")
public class Software {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "APP_NAME", nullable = false)
    private String name;

    @Column(name = "PACKAGE")
    private String appPackage;

    @Lob
    @Column(name = "PICTURE_128", length = 40000)
    private byte[] pictureContent128;

    @Lob
    @Column(name = "PICTURE_512", length = 40000)
    private byte[] pictureContent512;

    @Column(name = "DESCRIPTION", length = 1500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_NAME")
    private Category category;

    @Column(name = "DOWNLOADS_NUM")
    private Integer downloadsNumber;

    @Column(name = "TIME_UPLOADED")
    private Long timeUploaded;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public byte[] getPictureContent128() {
        return pictureContent128;
    }

    public void setPictureContent128(byte[] pictureContent128) {
        this.pictureContent128 = pictureContent128;
    }

    public byte[] getPictureContent512() {
        return pictureContent512;
    }

    public void setPictureContent512(byte[] pictureContent512) {
        this.pictureContent512 = pictureContent512;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getDownloadsNumber() {
        return downloadsNumber;
    }

    public void setDownloadsNumber(Integer downloadsNumber) {
        this.downloadsNumber = downloadsNumber;
    }

    public Long getTimeUploaded() {
        return timeUploaded;
    }

    public void setTimeUploaded(Long timeUploaded) {
        this.timeUploaded = timeUploaded;
    }

    @Override
    public String toString() {
        return "Software{"
                + ", name='" + name + '\''
                + ", appPackage='" + appPackage + '\''
                + ", description='" + description + '\''
                + ", category=" + category
                + ", downloadsNumber=" + downloadsNumber
                + ", timeUploaded=" + timeUploaded
                + '}';
    }
}
