package com.ivamly.beanbuddies.controller;

import com.ivamly.beanbuddies.model.CoffeeProducer;
import com.ivamly.beanbuddies.service.CoffeeProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producer")
public class CoffeeProducerController {
    private final CoffeeProducerService coffeeProducerService;

    public CoffeeProducerController(CoffeeProducerService coffeeProducerService) {
        this.coffeeProducerService = coffeeProducerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CoffeeProducer>> getAllCoffeeProducers() {
        List<CoffeeProducer> producers = coffeeProducerService.findAllCoffeeProducers();
        return ResponseEntity.ok(producers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoffeeProducer> getCoffeeProducerById(@PathVariable Long id) {
        Optional<CoffeeProducer> producer = coffeeProducerService.findCoffeeProducerById(id);
        return producer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CoffeeProducer> createCoffeeProducer(@RequestBody CoffeeProducer coffeeProducer) {
        CoffeeProducer savedCoffeeProducer = coffeeProducerService.saveCoffeeProducer(coffeeProducer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCoffeeProducer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoffeeProducer> updateCoffeeProducer(@PathVariable Long id, @RequestBody CoffeeProducer coffeeProducer) {
        Optional<CoffeeProducer> existingProducer = coffeeProducerService.findCoffeeProducerById(id);
        if (existingProducer.isPresent()) {
            coffeeProducer.setId(id);
            CoffeeProducer updatedCoffeeProducer = coffeeProducerService.saveCoffeeProducer(coffeeProducer);
            return ResponseEntity.ok(updatedCoffeeProducer);
        } else {
            CoffeeProducer newCoffeeProducer = coffeeProducerService.saveCoffeeProducer(coffeeProducer);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCoffeeProducer);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffeeProducer(@PathVariable Long id) {
        coffeeProducerService.deleteCoffeeProducer(id);
        return ResponseEntity.noContent().build();
    }
}
