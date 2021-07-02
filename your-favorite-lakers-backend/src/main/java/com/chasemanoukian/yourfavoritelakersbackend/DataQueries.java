package com.chasemanoukian.yourfavoritelakersbackend;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class DataQueries {
    private final String playerLink = "https://www.espn.com/nba/player/_/id/";

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
}
