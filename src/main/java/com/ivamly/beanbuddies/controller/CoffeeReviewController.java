package com.ivamly.beanbuddies.controller;

import com.ivamly.beanbuddies.model.CoffeeReview;
import com.ivamly.beanbuddies.service.CoffeeReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class CoffeeReviewController {
    private final CoffeeReviewService coffeeReviewService;

    public CoffeeReviewController(CoffeeReviewService coffeeReviewService) {
        this.coffeeReviewService = coffeeReviewService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CoffeeReview>> getAllCoffeeReviews() {
        List<CoffeeReview> reviews = coffeeReviewService.findAllCoffeeReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoffeeReview> getCoffeeReviewById(@PathVariable Long id) {
        Optional<CoffeeReview> review = coffeeReviewService.findCoffeeReviewById(id);
        return review.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CoffeeReview> createCoffeeReview(@RequestBody CoffeeReview review) {
        CoffeeReview savedCoffeeReview = coffeeReviewService.saveCoffeeReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCoffeeReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoffeeReview> updateCoffeeReview(@PathVariable Long id, @RequestBody CoffeeReview review) {
        Optional<CoffeeReview> existingReview = coffeeReviewService.findCoffeeReviewById(id);
        if (existingReview.isPresent()) {
            review.setId(id);
            CoffeeReview updatedCoffeeReview = coffeeReviewService.saveCoffeeReview(review);
            return ResponseEntity.ok(updatedCoffeeReview);
        } else {
            CoffeeReview newReview = coffeeReviewService.saveCoffeeReview(review);
            return ResponseEntity.status(HttpStatus.CREATED).body(newReview);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffeeReview(@PathVariable Long id) {
        coffeeReviewService.deleteCoffeeReview(id);
        return ResponseEntity.noContent().build();
    }
}
