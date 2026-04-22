package ru.cherkesova;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cherkesova.entity.Review;
import ru.cherkesova.repo.ReviewRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data")
public class DataController {

    private final ReviewRepository repository;

    public DataController(ReviewRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/search")
    public List<Review> search(@RequestParam String restaurant) {
        return repository.findByRestaurantName(restaurant);
    }

    @GetMapping("/report")
    public Map<String, Double> report() {

        List<Review> reviews = repository.findAll();

        return reviews.stream()
                .collect(Collectors.groupingBy(
                        r -> r.getRestaurant().getName(),
                        Collectors.averagingInt(Review::getRating)
                ));
    }

    @GetMapping("/report/popular")
    public List<Map<String, Object>> popular() {
        return repository.findMostPopularRestaurants()
                .stream()
                .map(r -> Map.of(
                        "restaurant", r[0],
                        "reviewCount", r[1]
                ))
                .toList();
    }

    @GetMapping("/report/top-rated")
    public List<Map<String, Object>> topRated() {
        return repository
                .findTopRatedRestaurants(PageRequest.of(0, 10))
                .stream()
                .map(r -> Map.of(
                        "restaurant", r[0],
                        "avgRating", r[1]
                ))
                .toList();
    }
}
