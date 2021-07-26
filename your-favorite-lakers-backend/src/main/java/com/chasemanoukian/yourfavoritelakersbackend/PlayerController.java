package com.chasemanoukian.yourfavoritelakersbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/crawl", produces = "application/json")
    public void crawlPlayerStats() {
    // CR QueueRunnable and execute
//        try {
//            Document doc = Jsoup.connect("https://www.espn.com/nba/team/roster/_/name/lal").get();
//            Elements els = doc.getElementsByTag("tr");
//
//            DataQueries queries = new DataQueries();
//            List<String> ids = queries.getIds(els);
//            ids.remove(0);
//        } catch(IOException e) {
//            e.printStackTrace();
//        }

        QueueRunnable qr = new QueueRunnable("1966", playerService);
        qr.executeRunnable();
    }
}
