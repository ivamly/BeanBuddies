package com.ivamly.beanbuddies.service;

import com.ivamly.beanbuddies.model.CoffeeReview;
import com.ivamly.beanbuddies.repository.CoffeeReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeReviewService {
    private final CoffeeReviewRepository coffeeReviewRepository;

    public CoffeeReviewService(CoffeeReviewRepository coffeeReviewRepository) {
        this.coffeeReviewRepository = coffeeReviewRepository;
    }

    public List<CoffeeReview> findAllCoffeeReviews() {
        return coffeeReviewRepository.findAll();
    }

    public Optional<CoffeeReview> findCoffeeReviewById(Long id) {
        return coffeeReviewRepository.findById(id);
    }

    public CoffeeReview saveCoffeeReview(CoffeeReview coffeeReview) {
        return coffeeReviewRepository.save(coffeeReview);
    }

    public Optional<CoffeeReview> updateCoffeeReview(Long id, CoffeeReview coffeeReview) {
        if (coffeeReviewRepository.existsById(id)) {
            coffeeReview.setId(id);
            CoffeeReview updatedCoffeeReview = coffeeReviewRepository.save(coffeeReview);
            return Optional.of(updatedCoffeeReview);
        }
        return Optional.empty();
    }

    public void deleteCoffeeReview(Long id) {
        coffeeReviewRepository.deleteById(id);
    }
}
