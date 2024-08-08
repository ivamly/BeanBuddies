package com.ivamly.beanbuddies;

import com.ivamly.beanbuddies.model.*;
import com.ivamly.beanbuddies.repository.CoffeeProducerRepository;
import com.ivamly.beanbuddies.repository.CoffeeRepository;
import com.ivamly.beanbuddies.repository.CoffeeReviewRepository;
import com.ivamly.beanbuddies.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
class DataLoader {
    private final CoffeeProducerRepository coffeeProducerRepository;
    private final CoffeeRepository coffeeRepository;
    private final UserRepository userRepository;
    private final CoffeeReviewRepository coffeeReviewRepository;

    DataLoader(CoffeeProducerRepository coffeeProducerRepository, CoffeeRepository coffeeRepository,
               UserRepository userRepository, CoffeeReviewRepository coffeeReviewRepository) {
        this.coffeeProducerRepository = coffeeProducerRepository;
        this.coffeeRepository = coffeeRepository;
        this.userRepository = userRepository;
        this.coffeeReviewRepository = coffeeReviewRepository;
    }

    @PostConstruct
    public void run() {
        CoffeeProducer producer1 = new CoffeeProducer();
        producer1.setName("Coffee Roasters Inc.");
        coffeeProducerRepository.save(producer1);

        CoffeeProducer producer2 = new CoffeeProducer();
        producer2.setName("Brew Masters");
        coffeeProducerRepository.save(producer2);

        Coffee coffee1 = new Coffee();
        coffee1.setName("Espresso");
        coffee1.setCountry("Italy");
        coffee1.setGridType(GridType.FINE);
        coffee1.setWeight(250);
        coffee1.setPrice(BigDecimal.valueOf(12.99));
        coffee1.setDescription("Strong and bold espresso.");
        coffee1.setProducer(producer1);
        coffeeRepository.save(coffee1);

        Coffee coffee2 = new Coffee();
        coffee2.setName("Colombian Blend");
        coffee2.setCountry("Colombia");
        coffee2.setGridType(GridType.MEDIUM);
        coffee2.setWeight(500);
        coffee2.setPrice(BigDecimal.valueOf(9.99));
        coffee2.setDescription("Smooth and rich Colombian coffee.");
        coffee2.setProducer(producer2);
        coffeeRepository.save(coffee2);

        User user1 = new User();
        user1.setUsername("john_doe");
        user1.setCoffees(Arrays.asList(coffee1, coffee2));
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("jane_smith");
        user2.setCoffees(List.of(coffee1));
        userRepository.save(user2);

        // Creating and saving reviews
        CoffeeReview review1 = new CoffeeReview();
        review1.setUser(user1);
        review1.setCoffee(coffee1);
        review1.setRating(8);
        review1.setComment("Great espresso with a strong kick!");
        coffeeReviewRepository.save(review1);

        CoffeeReview review2 = new CoffeeReview();
        review2.setUser(user1);
        review2.setCoffee(coffee2);
        review2.setRating(7);
        review2.setComment("Smooth and enjoyable, but a bit pricey.");
        coffeeReviewRepository.save(review2);

        CoffeeReview review3 = new CoffeeReview();
        review3.setUser(user2);
        review3.setCoffee(coffee1);
        review3.setRating(9);
        review3.setComment("Excellent espresso! Will buy again.");
        coffeeReviewRepository.save(review3);

        CoffeeReview review4 = new CoffeeReview();
        review4.setUser(user2);
        review4.setCoffee(coffee2);
        review4.setRating(6);
        review4.setComment("Not bad, but I've had better Colombian coffee.");
        coffeeReviewRepository.save(review4);
    }
}
