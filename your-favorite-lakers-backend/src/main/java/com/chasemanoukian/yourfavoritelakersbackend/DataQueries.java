package com.chasemanoukian.yourfavoritelakersbackend;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataQueries {
    private static final String playerLink = "https://www.espn.com/nba/player/_/id/";

    public List<String> getIds(Elements els) {
        List<String> ids = new ArrayList<>();
        for (Element el:els) {
            String playerUrl = el.select("a").first().attr("href");
            String id = playerUrl.replace(playerLink, "");
            id = id.substring(0, id.indexOf("/"));
            ids.add(id);
        }

        return ids;
    }

    public Map<String, String> getStats(Document doc, String className, int getIdx, int byIdx) {
        Map<String, String> stats = new HashMap<>();

        Elements els = doc
                .getElementsByClass(className)
                .select("[data-idx=0]")
                .get(getIdx)
                .children();

        if (className != "PlayerStats") stats.put("prev", els.get(0).text() + " " + els.get(1).text());
        for (int i = 0; i < byIdx; i++) {
            els.remove(0);
        }
        els.remove(4);
        els.remove(4);

        stats.put("PTS", els.get(4).text());
        stats.put("REB", els.get(0).text());
        stats.put("AST", els.get(1).text());
        stats.put("STL", els.get(3).text());
        stats.put("BLK", els.get(2).text());

        return stats;
    }
}
