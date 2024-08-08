package com.ivamly.beanbuddies.service;

import com.ivamly.beanbuddies.model.Coffee;
import com.ivamly.beanbuddies.repository.CoffeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public Page<Coffee> findAllCoffees(Pageable pageable) {
        return coffeeRepository.findAll(pageable);
    }

    public Optional<Coffee> findCoffeeById(Long id) {
        return coffeeRepository.findById(id);
    }

    public Coffee saveCoffee(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    public Optional<Coffee> updateCoffee(Long id, Coffee coffee) {
        if (coffeeRepository.existsById(id)) {
            coffee.setId(id);
            Coffee updatedCoffee = coffeeRepository.save(coffee);
            return Optional.of(updatedCoffee);
        }
        return Optional.empty();
    }

    public void deleteCoffee(Long id) {
        coffeeRepository.deleteById(id);
    }
}
