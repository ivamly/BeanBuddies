package com.ivamly.beanbuddies.repository;

import com.ivamly.beanbuddies.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
