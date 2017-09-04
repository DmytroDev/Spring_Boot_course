package com.itcompany.softwarestore.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * JPA entity that is mapped to the table USERS. Stores the following information:
 * <ul>
 * <li>username;</li>
 * <li>password;</li>
 * <li>enabled;</li>
 * <li>user roles;</li>
 * </ul>
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "USERS")
public class User {

    private String username;
    private String password;
    private boolean enabled;
    private Set<UserRole> userRole = new HashSet<>(0);

    @Id
    @Column(name = "USERNAME", unique = true, nullable = false, length = 45)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD", nullable = false, length = 100)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "ENABLED", nullable = false)
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", enabled=" + enabled
                + ", userRole=" + userRole
                + '}';
    }
}
