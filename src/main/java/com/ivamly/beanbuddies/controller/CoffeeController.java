package com.ivamly.beanbuddies.controller;

import com.ivamly.beanbuddies.model.Coffee;
import com.ivamly.beanbuddies.service.CoffeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {
    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Coffee>> getAllCoffees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.unsorted());

        Page<Coffee> coffees = coffeeService.findAllCoffees(pageable);

        return ResponseEntity.ok(coffees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable Long id) {
        Optional<Coffee> coffee = coffeeService.findCoffeeById(id);
        return coffee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Coffee> createCoffee(@RequestBody Coffee coffee) {
        Coffee savedCoffee = coffeeService.saveCoffee(coffee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCoffee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(
            @PathVariable Long id,
            @RequestBody Coffee coffee) {
        Optional<Coffee> existingCoffee = coffeeService.findCoffeeById(id);
        if (existingCoffee.isPresent()) {
            coffee.setId(id);
            Coffee updatedCoffee = coffeeService.saveCoffee(coffee);
            return ResponseEntity.ok(updatedCoffee);
        } else {
            Coffee newCoffee = coffeeService.saveCoffee(coffee);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCoffee);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable Long id) {
        coffeeService.deleteCoffee(id);
        return ResponseEntity.noContent().build();
    }
}
