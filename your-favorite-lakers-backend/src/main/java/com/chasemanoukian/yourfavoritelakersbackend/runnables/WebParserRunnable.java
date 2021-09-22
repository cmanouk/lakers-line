package com.chasemanoukian.yourfavoritelakersbackend.runnables;

import com.chasemanoukian.yourfavoritelakersbackend.WebParserMethods;
import com.chasemanoukian.yourfavoritelakersbackend.models.Player;
import com.chasemanoukian.yourfavoritelakersbackend.services.PlayerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WebParserRunnable implements Runnable {
    private final String _id;
    private final String link;
    private final PlayerService playerService;

    public WebParserRunnable(String _id, String link, PlayerService playerService) {
        this._id = _id;
        this.link = link;
        this.playerService = playerService;
    }

    @Override
    public void run() {
        try {
            Document doc = Jsoup.connect(link).get();
            WebParserMethods dq = new WebParserMethods();

            List<String> playerName = dq.getPlayerName(doc);
            List<String> jerseyAndPosition = dq.getJerseyAndPosition(doc);
            List<String> playerBio = dq.getPlayerBio(doc);
            Map<String, String> seasonStats = dq.getStats(doc, "PlayerStats", 1, 5);
            Map<String, String> gameStats = dq.getStats(doc, "gamelogWidget--basketball", 0, 7);

            String gameStatsKey = gameStats.get("prev");
            gameStats.remove("prev");

            Player player = playerService.findById(_id);
            if (player != null) {
                player.updatePrevTen(gameStatsKey, gameStats);
                playerService.update(player);
            } else {
                Player newPlayer = new Player();
                newPlayer.setAllProperties(_id, playerName, jerseyAndPosition, playerBio, seasonStats);
                playerService.create(newPlayer);

                // Implement logic for initial prev 10 AND prev seasons
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
