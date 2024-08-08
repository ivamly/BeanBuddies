package com.ivamly.beanbuddies.service;

import com.ivamly.beanbuddies.model.CoffeeProducer;
import com.ivamly.beanbuddies.repository.CoffeeProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeProducerService {
    private final CoffeeProducerRepository coffeeProducerRepository;

    public CoffeeProducerService(CoffeeProducerRepository coffeeProducerRepository) {
        this.coffeeProducerRepository = coffeeProducerRepository;
    }

    public List<CoffeeProducer> findAllCoffeeProducers() {
        return coffeeProducerRepository.findAll();
    }

    public Optional<CoffeeProducer> findCoffeeProducerById(Long id) {
        return coffeeProducerRepository.findById(id);
    }

    public CoffeeProducer saveCoffeeProducer(CoffeeProducer coffeeProducer) {
        return coffeeProducerRepository.save(coffeeProducer);
    }

    public Optional<CoffeeProducer> updateCoffeeProducer(Long id, CoffeeProducer coffeeProducer) {
        if (coffeeProducerRepository.existsById(id)) {
            coffeeProducer.setId(id);
            coffeeProducerRepository.save(coffeeProducer);
            return Optional.of(coffeeProducer);
        }
        return Optional.empty();
    }

    public void deleteCoffeeProducer(Long id) {
        coffeeProducerRepository.deleteById(id);
    }
}
