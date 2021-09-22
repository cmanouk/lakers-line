package com.chasemanoukian.yourfavoritelakersbackend.controllers;

import com.chasemanoukian.yourfavoritelakersbackend.models.Player;
import com.chasemanoukian.yourfavoritelakersbackend.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/players")
public class PlayerRestController {
    @Autowired
    private PlayerService playerService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Player savePlayer(@RequestBody Player player) {
        return playerService.create(player);
    }

    @GetMapping(produces = "application/json")
    public List<Player> getAllPlayers() {
        return playerService.read();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Player findById(@PathVariable String id) {
        return playerService.findById(id);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Player updatePlayer(@RequestBody Player player) {
        return playerService.update(player);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deletePlayer(@PathVariable String id) {
        return playerService.delete(id);
    }
}
