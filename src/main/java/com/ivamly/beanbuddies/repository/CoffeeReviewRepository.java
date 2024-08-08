package com.ivamly.beanbuddies.repository;

import com.ivamly.beanbuddies.model.CoffeeReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeReviewRepository extends JpaRepository<CoffeeReview, Long> {
}
