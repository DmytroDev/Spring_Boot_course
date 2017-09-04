package com.itcompany.softwarestore.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * JPA entity that is mapped to the table USER_ROLES. Stores the following information:
 * <ul>
 * <li>role id;</li>
 * <li>user;</li>
 * <li>role name;</li>
 * </ul>
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "USER_ROLES", uniqueConstraints = @UniqueConstraint(columnNames = {"ROLE", "USERNAME"}))
public class UserRole {

    private Integer userRoleId;
    private User user;
    private String role;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "USER_ROLE_ID", unique = true, nullable = false)
    public Integer getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERNAME", nullable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "ROLE", nullable = false, length = 45)
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{"
                + "userRoleId=" + userRoleId
                + ", user=" + user.getUsername()
                + ", role='" + role + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRole userRole = (UserRole) o;

        if (!userRoleId.equals(userRole.userRoleId)) {
            return false;
        }
        if (!user.equals(userRole.user)) {
            return false;
        }
        return role.equals(userRole.role);
    }

    @Override
    public int hashCode() {
        int result = userRoleId.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
