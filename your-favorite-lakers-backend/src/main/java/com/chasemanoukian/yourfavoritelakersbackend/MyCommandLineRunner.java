package com.chasemanoukian.yourfavoritelakersbackend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Order(0)
public class MyCommandLineRunner implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            Document doc = Jsoup.connect("https://www.espn.com/nba/team/roster/_/name/lal").get();
            Elements els = doc.getElementsByTag("tr");

            DataQueries queries = new DataQueries();
            List<String> ids = queries.getIds(els);

            for (String id : ids) {

            }
            System.out.println(ids.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
