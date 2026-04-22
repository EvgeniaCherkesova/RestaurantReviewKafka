package ru.cherkesova;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cherkesova.entity.Restaurant;
import ru.cherkesova.entity.Review;
import ru.cherkesova.repo.RestaurantRepository;
import ru.cherkesova.repo.ReviewRepository;

import java.time.LocalDateTime;

@Service
@Transactional
public class KafkaConsumerService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    public KafkaConsumerService(ReviewRepository reviewRepository,
                                RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @KafkaListener(topics = "reviews-topic", groupId = "review-group")
    public void consume(ReviewDto dto) {

        Restaurant restaurant = restaurantRepository
                .findByName(dto.restaurantName)
                .orElseGet(() -> {
                    Restaurant r = new Restaurant();
                    r.setName(dto.restaurantName);
                    return restaurantRepository.save(r);
                });

        Review review = new Review();
        review.setRestaurant(restaurant);
        review.setRating(dto.rating);
        review.setComment(dto.comment);
        review.setCreatedAt(LocalDateTime.now());

        reviewRepository.save(review);
    }
}