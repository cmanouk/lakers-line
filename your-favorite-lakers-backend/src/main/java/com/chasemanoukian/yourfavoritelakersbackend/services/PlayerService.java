package com.chasemanoukian.yourfavoritelakersbackend.services;

import com.chasemanoukian.yourfavoritelakersbackend.models.Player;

import java.util.List;
import java.util.Map;

public interface PlayerService {
    Player create(Player player);
    List<Player> read();
    Player findById(String id);
    Player update(Player player);
    Map<String, String> delete(String id);
}
