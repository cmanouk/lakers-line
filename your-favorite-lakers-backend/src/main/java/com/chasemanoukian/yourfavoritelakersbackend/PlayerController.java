package com.chasemanoukian.yourfavoritelakersbackend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    @GetMapping(value = "/crawl/update", produces = "application/json")
    public void crawlPlayerStats() {
    // CR QueueRunnable and execute
        try {
            Document doc = Jsoup.connect("https://www.espn.com/nba/team/roster/_/name/lal").get();
            Elements els = doc.getElementsByTag("tr");

            DataQueries queries = new DataQueries();
            List<String> ids = queries.getIds(els);
            ids.remove(0);

            List<Player> currentPlayersInDb = playerService.read();
            for (Player p : currentPlayersInDb) {
                if (!ids.contains(p.get_id())) {
                    playerService.delete(p.get_id());
                }
            }

            for (String id : ids) {
                QueueRunnable qr = new QueueRunnable(id, playerService);
                qr.executeRunnable();

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/prevten/{id}", produces = "application/json")
    public void crawlPrevTenById(@PathVariable String id) {
        QueueRunnable qr = new QueueRunnable(id, playerService);
        qr.executePrevTenRunnable();
    }
}
