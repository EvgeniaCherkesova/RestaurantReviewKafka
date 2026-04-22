package ru.cherkesova;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final KafkaProducerService producerService;
    private final RestTemplate restTemplate;

    public ApiController(KafkaProducerService producerService) {
        this.producerService = producerService;
        this.restTemplate = new RestTemplate();
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@RequestBody ReviewDto review) {
        producerService.sendReview(review);
        return ResponseEntity.ok("Sent to Kafka");
    }

    @GetMapping("/search")
    public String search(@RequestParam String restaurant) {
        return restTemplate.getForObject(
                "http://data-service:8081/data/search?restaurant=" + restaurant,
                String.class
        );
    }

    @GetMapping("/report")
    public String report() {
        return restTemplate.getForObject(
                "http://data-service:8081/data/report",
                String.class
        );
    }

    @GetMapping("/report/top-rated")
    public String topRated() {
        return restTemplate.getForObject(
                "http://data-service:8081/data/report/top-rated",
                String.class
        );
    }

    @GetMapping("/report/popular")
    public String popular() {
        return restTemplate.getForObject(
                "http://data-service:8081/data/report/popular",
                String.class
        );
    }
}