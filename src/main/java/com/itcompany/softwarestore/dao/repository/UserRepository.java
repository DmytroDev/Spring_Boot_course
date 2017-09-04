package com.itcompany.softwarestore.dao.repository;

import com.itcompany.softwarestore.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository that provides basic CRUD operations. Implemented when context is being started up.
 *
 * @author Dmitriy Nadolenko
 * @version 1.0
 * @since 1.0
 */
public interface UserRepository extends JpaRepository<User, String> {
}
