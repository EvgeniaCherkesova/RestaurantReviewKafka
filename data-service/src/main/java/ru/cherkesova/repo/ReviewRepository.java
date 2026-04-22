package ru.cherkesova.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.cherkesova.entity.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByRestaurantName(String name);

    @Query("""
    SELECT r.restaurant.name, COUNT(r)
    FROM Review r
    GROUP BY r.restaurant.name
    ORDER BY COUNT(r) DESC
    """)
    List<Object[]> findMostPopularRestaurants();

    @Query("""
    SELECT r.restaurant.name, AVG(r.rating)
    FROM Review r
    GROUP BY r.restaurant.name
    ORDER BY AVG(r.rating) DESC
    """)
    List<Object[]> findTopRatedRestaurants(Pageable pageable);
}