package com.chasemanoukian.yourfavoritelakersbackend;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface PlayerService {
    Player create(Player player);
    List<Player> read();
    Optional<Player> findById(String id);
    Player update(Player player);
    Map<String, String> delete(String id);
}
