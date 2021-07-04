package com.chasemanoukian.yourfavoritelakersbackend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

public class DataQueriesRunnable implements Runnable {
    private static int count = 0;
    private String _id;
    private String link;

    public DataQueriesRunnable(String _id, String link) {
        this._id = _id;
        this.link = link.concat(_id);
    }

    @Override
    public void run() {
        try {
            Document doc = Jsoup.connect(link).get();
            DataQueries dq = new DataQueries();

            Map<String, String> seasonStats = dq.getStats(doc, "PlayerStats", 1, 5);
            Map<String, String> gameStats = dq.getStats(doc, "gamelogWidget--basketball", 0, 7);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
