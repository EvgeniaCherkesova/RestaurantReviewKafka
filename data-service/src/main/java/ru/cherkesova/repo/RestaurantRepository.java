package ru.cherkesova.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cherkesova.entity.Restaurant;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    Optional<Restaurant> findByName(String name);
}