package com.chasemanoukian.yourfavoritelakersbackend.services;

import com.chasemanoukian.yourfavoritelakersbackend.models.Player;
import com.chasemanoukian.yourfavoritelakersbackend.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlayerServiceImplementation implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player create(Player player) {
        return playerRepository.insert(player);
    }

    @Override
    public List<Player> read() {
        return playerRepository.findAll();
    }

    @Override
    public Player findById(String id) {
        return playerRepository.getPlayerById(id);
    }

    @Override
    public Player update(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Map<String, String> delete(String id) {
        playerRepository.deleteById(id);
        return null;
    }
}
