package com.chasemanoukian.yourfavoritelakersbackend.runnables;

import com.chasemanoukian.yourfavoritelakersbackend.models.Player;
import com.chasemanoukian.yourfavoritelakersbackend.services.PlayerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class PrevSeasonsStatsRunnable implements Runnable {
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

            Elements els = doc
                    .getElementsByClass("Table__TBODY");
            List<Element> filteredEls = new ArrayList<>();

            for (int i = 0; i < 2; i++) {
                Element el = els.get(i);
                filteredEls.add(el);
            }

            Map<String, Map<String, String>> prevSeasonStats = new LinkedHashMap<>();
            for (int i = 0; i < filteredEls.get(0).childrenSize() - 1; i++) {
                String label = filteredEls.get(0).child(i).text();
                Element el = filteredEls.get(1).child(i);

                Map<String, String> stats = new HashMap<>();
                stats.put("GP", el.child(0).text());
                for (int t = 0; t < 11; t++) {
                    el.child(0).remove();
                }

                stats.put("PTS", el.child(6).text());
                stats.put("REB", el.child(0).text());
                stats.put("AST", el.child(1).text());
                stats.put("STL", el.child(3).text());
                stats.put("BLK", el.child(2).text());

                prevSeasonStats.put(label, stats);
            }

            Player player = playerService.findById(_id);
            player.setPrevSeasons(prevSeasonStats);
            playerService.update(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
