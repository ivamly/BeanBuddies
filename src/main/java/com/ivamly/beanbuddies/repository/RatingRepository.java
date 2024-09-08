package com.ivamly.beanbuddies.repository;

import com.ivamly.beanbuddies.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
