package ru.cherkesova;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final KafkaProducerService producerService;
    private final RestTemplate restTemplate;
    private final DataServiceClient client;

    public ApiController(KafkaProducerService producerService, DataServiceClient client) {
        this.producerService = producerService;
        this.restTemplate = new RestTemplate();
        this.client = client;
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@RequestBody ReviewDto review) {
        producerService.sendReview(review);
        return ResponseEntity.ok("Sent to Kafka");
    }

    @GetMapping("/search")
    public String search(@RequestParam String restaurant) {
        return client.search(restaurant);
    }

    @GetMapping("/report")
    public String report() {
        return client.getReport();
    }

    @GetMapping("/report/top-rated")
    public String topRated() {
        return client.getTopRated();
    }

    @GetMapping("/report/popular")
    public String popular() {
        return client.getPopular();
    }
}