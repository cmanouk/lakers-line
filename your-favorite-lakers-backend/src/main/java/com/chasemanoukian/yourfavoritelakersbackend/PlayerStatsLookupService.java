package com.chasemanoukian.yourfavoritelakersbackend;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class PlayerStatsLookupService {
    private final RestTemplate restTemplate;

    public PlayerStatsLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<Object> getStats(String id) throws InterruptedException {
        String url = String.format("https://api-nba-v1.p.rapidapi.com/statistics/players/gameId/%s", id);
        Object results = restTemplate.getForObject(url, Object.class);
        return CompletableFuture.completedFuture(results);
    }

}
