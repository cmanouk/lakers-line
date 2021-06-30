package com.chasemanoukian.yourfavoritelakersbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/players")
public class PlayerController {
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
}
