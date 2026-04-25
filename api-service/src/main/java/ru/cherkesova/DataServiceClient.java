package ru.cherkesova;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataServiceClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public DataServiceClient(RestTemplate restTemplate,
                             @Value("${data.service.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public String search(String restaurant) {
        String url = baseUrl + "/search?restaurant=" + restaurant;
        return restTemplate.getForObject(url, String.class);
    }

    public String getPopular() {
        return restTemplate.getForObject(baseUrl + "/report/popular", String.class);
    }

    public String getTopRated() {
        return restTemplate.getForObject(baseUrl + "/report/top-rated", String.class);
    }

    public String getReport(){
        return restTemplate.getForObject(baseUrl + "/report", String.class);
    }
}