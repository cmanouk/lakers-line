package com.chasemanoukian.yourfavoritelakersbackend.webparser;

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
    private final String previousTenLink;
    private final String previousSeasonsLink;
    private final PlayerService playerService;

    public WebParserRunnable(String _id, PlayerService playerService) {
        this._id = _id;
        this.link = "https://www.espn.com/nba/player/_/id/" + _id;
        this.previousTenLink = "https://www.espn.com/nba/player/gamelog/_/id/" + _id;
        this.previousSeasonsLink = "https://www.espn.com/nba/player/stats/_/id/" + _id;
        this.playerService = playerService;
    }

    @Override
    public void run() {
        try {
            Document doc = Jsoup.connect(link).get();
            WebParserMethods dq = new WebParserMethods();

            Player player = playerService.findById(_id);
            if (player != null) {
                Map<String, String> gameStats = dq.getStats(doc, "gamelogWidget--basketball", 0, 7);
                String gameStatsKey = gameStats.get("prev");
                gameStats.remove("prev");

                player.updatePrevTen(gameStatsKey, gameStats);
                playerService.update(player);
            } else {
                List<String> playerName = dq.getPlayerName(doc);
                List<String> jerseyAndPosition = dq.getJerseyAndPosition(doc);
                List<String> playerBio = dq.getPlayerBio(doc);
                Map<String, String> seasonStats = dq.getStats(doc, "PlayerStats", 1, 5);

                Player newPlayer = new Player();
                newPlayer.setAllProperties(_id, playerName, jerseyAndPosition, playerBio, seasonStats);

                try {
                    doc = Jsoup.connect(previousTenLink).get();
                    Map<String, Map<String, String>> prevTen = dq.getInitialPrevTen(doc);
                    newPlayer.setPrevTen(prevTen);
                } catch(IOException e) {
                    e.printStackTrace();
                }

                try {
                    doc = Jsoup.connect(previousSeasonsLink).get();
                    Map<String, Map<String, String>> prevSeasonStats = dq.getInitialPreviousSeasonStats(doc);
                    newPlayer.setPrevSeasons(prevSeasonStats);
                } catch(IOException e) {
                    e.printStackTrace();
                }

                playerService.create(newPlayer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
