package com.chasemanoukian.yourfavoritelakersbackend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataQueriesRunnable implements Runnable {
    private String _id;
    private String link;
    private PlayerService playerService;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public PlayerService getPlayerService() {
        return playerService;
    }

    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public void run() {
        try {
            Document doc = Jsoup.connect(link).get();
            DataQueries dq = new DataQueries();

            List<String> jerseyAndPosition = dq.getJerseyAndPosition(doc);
            List<String> playerBio = dq.getPlayerBio(doc);
            Map<String, String> seasonStats = dq.getStats(doc, "PlayerStats", 1, 5);
            Map<String, String> gameStats = dq.getStats(doc, "gamelogWidget--basketball", 0, 7);

            String gameStatsKey = gameStats.get("prev");
            gameStats.remove("prev");

            Player player = playerService.findById(_id);
            if (player != null) {
                Map<String, Map<String, String>> prevTen = player.getPrevTen();
                if (!prevTen.containsKey(gameStatsKey)) {
                    prevTen.put(gameStatsKey, gameStats);
                    player.setSeasonStats(seasonStats);
                    playerService.update(player);
                }
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
