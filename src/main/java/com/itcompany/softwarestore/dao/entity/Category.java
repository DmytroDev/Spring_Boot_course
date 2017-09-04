package com.itcompany.softwarestore.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JPA entity that is mapped to the table CATEGORY. Stores the following information:
 * <ul>
 * <li>category name;</li>
 * <li>category description;</li>
 * </ul>
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @Column(name = "CATEGORY_NAME")
    private String name;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{"
                + "name='" + name + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
