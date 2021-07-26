package com.chasemanoukian.yourfavoritelakersbackend;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

//@Component
//@Order(0)
public class MyCommandLineRunner implements ApplicationListener<ApplicationReadyEvent> {
//    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        try {
//            Document doc = Jsoup.connect("https://www.espn.com/nba/team/roster/_/name/lal").get();
//            Elements els = doc.getElementsByTag("tr");
//
//            DataQueries queries = new DataQueries();
//            List<String> ids = queries.getIds(els);
//            ids.remove(0);

            // Testing full list of player ids
//            for (String id : ids) {
//                queries.testRunnable(id);
//            }
            // Testing LBJ document
//            queries.testRunnable("1966");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
