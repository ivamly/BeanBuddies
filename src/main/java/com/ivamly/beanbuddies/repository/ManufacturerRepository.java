package com.ivamly.beanbuddies.repository;

import com.ivamly.beanbuddies.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
