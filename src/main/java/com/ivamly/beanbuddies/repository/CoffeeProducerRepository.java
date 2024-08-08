package com.ivamly.beanbuddies.repository;

import com.ivamly.beanbuddies.model.CoffeeProducer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeProducerRepository extends JpaRepository<CoffeeProducer, Long> {
}
