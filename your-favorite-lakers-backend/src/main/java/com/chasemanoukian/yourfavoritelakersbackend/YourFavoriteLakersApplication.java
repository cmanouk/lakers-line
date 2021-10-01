package com.chasemanoukian.yourfavoritelakersbackend;

import com.chasemanoukian.yourfavoritelakersbackend.models.Player;
import com.chasemanoukian.yourfavoritelakersbackend.services.PlayerService;
import com.chasemanoukian.yourfavoritelakersbackend.webparser.QueueRunnable;
import com.chasemanoukian.yourfavoritelakersbackend.webparser.WebParserMethods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
@EnableScheduling
public class YourFavoriteLakersApplication {

	public static void main(String[] args) {
		SpringApplication.run(YourFavoriteLakersApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Component
	public class Scheduler {
		@Autowired
		private PlayerService playerService;

		@PostConstruct
		@Scheduled(cron = "0 0 23 * * *")
		public void executeScheduler() {
			try {
				Document doc = Jsoup.connect("https://www.espn.com/nba/team/roster/_/name/lal").get();
				Elements els = doc.getElementsByTag("tr");

				// Grab all ids for players currently on roster
				WebParserMethods queries = new WebParserMethods();
				List<String> ids = queries.getIds(els);
				ids.remove(0);

				// check ids against db and delete players that are no longer on roster
				List<Player> currentPlayersInDb = playerService.read();
				for (Player p : currentPlayersInDb) {
					if (!ids.contains(p.get_id())) {
						playerService.delete(p.get_id());
					}
				}

				// run separate threads for each player => crawl and parse newest data
				for (String id : ids) {
					QueueRunnable qr = new QueueRunnable(id, playerService);
					qr.executeRunnable();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
