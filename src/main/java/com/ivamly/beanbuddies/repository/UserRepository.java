package com.ivamly.beanbuddies.repository;

import com.ivamly.beanbuddies.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
